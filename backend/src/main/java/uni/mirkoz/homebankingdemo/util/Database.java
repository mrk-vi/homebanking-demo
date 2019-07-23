package uni.mirkoz.homebankingdemo.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.*;
import uni.mirkoz.homebankingdemo.repository.users.AdministratorRepository;
import uni.mirkoz.homebankingdemo.service.contract.*;

import java.sql.Time;
import java.util.List;

@Component
public class Database {

    @Bean
    CommandLineRunner initDatabase(
            AdministratorRepository administratorRepository,
            AdministratorService administratorService,
            BankManagerService bankManagerService,
            VisitorService visitorService,
            EmployeeService employeeService,
            CustomerService customerService,
            PasswordEncoder encoder) {
        return args -> {
            administratorRepository.save(Administrator.builder()
                    .user(User.builder()
                            .username("admin")
                            .mail("admin@localhost.com")
                            .password(encoder.encode("admin"))
                            .status(Status.ENABLED)
                            .build())
                    .build());
            for (int i = 0; i < 3; i++) {
                Bank bank = administratorService.saveBank(
                        Bank.builder()
                                .name("Bank "+i)
                                .description("Description bank "+i)
                                .address("Address "+i)
                                .build(),
                        BankManager.builder()
                                .user(User.builder()
                                        .username("manager"+i)
                                        .mail("manager"+i+"@localhost.com")
                                        .status(Status.ENABLED)
                                        .build())
                                .build()
                );
                bankManagerService.createBankService(bank,
                        BankService.builder()
                                .type(BankService.Type.CAR_TAX)
                                .build());
                bankManagerService.createBankProduct(bank,
                        BankProduct.builder()
                                .type(BankProduct.Type.ACCT)
                                .name("Default ACCT "+i)
                                .interestRate(Float.valueOf("0.25"))
                                .build());
                bankManagerService.createBankProduct(bank,
                        BankProduct.builder()
                                .type(BankProduct.Type.PREPAID)
                                .name("Default PREPAID "+i)
                                .interestRate(Float.valueOf("0.0"))
                                .build());
                for (int j = 0; j < 2; j++) {
                    BankBranch bankBranch = bankManagerService.createBankBranch(
                            bank,
                            BankBranch.builder()
                                    .name(bank.getName()+" branch "+i+""+j)
                                    .address("branch address "+i+""+j)
                                    .opening(Time.valueOf("09:00:00"))
                                    .closing(Time.valueOf("18:00:00"))
                                    .build()
                    );
                    Employee employee = bankManagerService.assignEmployee(
                            bankBranch,
                            Employee.builder()
                                    .user(User.builder()
                                            .mail("employee"+i+""+j+"@localhost.com")
                                            .username("employee"+i+""+j)
                                            .status(Status.ENABLED)
                                            .build())
                                    .build()
                    );
                    Customer customer = visitorService.sendRegistrationRequest(
                            Customer.builder()
                                    .user(User.builder()
                                            .mail("customer"+i+""+j+"@localhost.com")
                                            .username("customer"+i+""+j)
                                            .password("password")
                                            .build())
                                    .build(),
                            bankBranch.getId()
                    );
                    administratorService.authorizeCustomer(customer.getId());
                    List<BankAccount> accounts = customerService.getBankAccountsByUser(customer.getUser());
                    accounts.forEach( account -> {
                        BankingOperation op1 = customerService.makeDeposit(customer.getUser(), account.getId(), 100F);
                        BankingOperation op2 = customerService.makeWithdraw(customer.getUser(), account.getId(), 50F);
                        BankingOperation op3 = customerService.makeDeposit(customer.getUser(), account.getId(), 150F);
                        BankingOperation op4 = customerService.makeTransfer(customer.getUser(), account.getId(), 30F, accounts.get(1).getIban());
                        employeeService.authorizeBankingOperation(employee, op1.getId());
                        employeeService.authorizeBankingOperation(employee, op2.getId());
                        employeeService.negateBankingOperation(employee, op3.getId());
                    });
                }
            }
        };
    }
}
