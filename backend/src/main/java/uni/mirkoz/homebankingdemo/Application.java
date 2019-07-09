package uni.mirkoz.homebankingdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Administrator;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.users.AdministratorRepository;
import uni.mirkoz.homebankingdemo.service.AdministratorService;
import uni.mirkoz.homebankingdemo.service.BankManagerService;

import java.sql.Time;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(uni.mirkoz.homebankingdemo.Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
            AdministratorRepository administratorRepository,
            AdministratorService administratorService,
            BankManagerService bankManagerService) {
        return args -> {
            administratorRepository.save(Administrator.builder()
                    .user(User.builder()
                            .username("admin")
                            .mail("admin@localhost.com")
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
                                        .build())
                                .build()
                );
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
                    bankManagerService.assignEmployee(
                            bankBranch,
                            Employee.builder()
                                    .user(User.builder()
                                            .mail("employee"+i+""+j+"@localhost.com")
                                            .username("employee"+i+""+j)
                                            .build())
                                    .build()
                    );
                }
                bankManagerService.createBankService(bank,
                        BankService.builder()
                                .type(BankService.Type.CAR_TAX)
                                .build());
                bankManagerService.createBankProduct(bank,
                        BankProduct.builder()
                                .type(BankProduct.Type.ACCT)
                                .name("Defaul ACCT "+i)
                                .interestRate(Float.valueOf("0.25"))
                                .build());
                bankManagerService.createBankProduct(bank,
                        BankProduct.builder()
                                .type(BankProduct.Type.PREPAID)
                                .name("Defaul PREPAID "+i)
                                .interestRate(Float.valueOf("0.0"))
                                .build());
            }
        };

    }
}