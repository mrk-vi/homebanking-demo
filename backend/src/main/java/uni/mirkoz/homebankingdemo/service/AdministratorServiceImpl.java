package uni.mirkoz.homebankingdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.accounts.BankAccountRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankProductRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.users.BankManagerRepository;
import uni.mirkoz.homebankingdemo.repository.users.CustomerRepository;
import uni.mirkoz.homebankingdemo.service.contract.AdministratorService;
import uni.mirkoz.homebankingdemo.util.PasswordGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Component
public class AdministratorServiceImpl implements AdministratorService {

    private Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    private BankRepository bankRepository;
    private BankManagerRepository bankManagerRepository;
    private CustomerRepository customerRepository;
    private BankProductRepository bankProductRepository;
    private BankAccountRepository bankAccountRepository;
    private PasswordGenerator passwordGenerator;
    private PasswordEncoder passwordEncoder;

    public AdministratorServiceImpl(BankRepository bankRepository, BankManagerRepository bankManagerRepository, CustomerRepository customerRepository, BankProductRepository bankProductRepository, BankAccountRepository bankAccountRepository, PasswordGenerator passwordGenerator, PasswordEncoder passwordEncoder) {
        this.bankRepository = bankRepository;
        this.bankManagerRepository = bankManagerRepository;
        this.customerRepository = customerRepository;
        this.bankProductRepository = bankProductRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Bank assignBankManager(Bank bank, BankManager bankManager) {
        ceaseBankManager(bank);
        try {
            bankManager = bankManagerRepository.findBankManagerByUser_UsernameOrUser_Mail(
                    bankManager.getUser().getUsername(),
                    bankManager.getUser().getMail()
            ).get();
        } catch (NoSuchElementException nsee) {}
        return saveBank(bank, bankManager);
    }

    @Override
    public Bank ceaseBankManager(Bank bank) {
        BankManager previousManager = bankManagerRepository
                .findBankManagerByBankAndStatus(bank, Status.AUTHORIZED)
                .get();
        try{
            previousManager.setStatus(Status.CEASED);
            bankManagerRepository.save(previousManager);
        } catch (NullPointerException npe){}
        return bankRepository.save(bank);
    }

    @Override
    public Bank saveBank(Bank bank, BankManager bankManager) {
        boolean hasBankManager = true;
        try {
            User user = bankManager.getUser();
            if (!user.getMail().isEmpty() && !user.getUsername().isEmpty()) {
                String password = passwordGenerator.newPassword();
                user.setPassword(passwordEncoder.encode(password));
                user.setStatus(Status.ENABLED);
                bankManager.setStatus(Status.AUTHORIZED);
                bankManager = bankManagerRepository.save(bankManager);
                logger.info(String.format("[new bankmanager credentials in clear] - username: %s - password %s",
                        user.getUsername(), password));
            } else {
                hasBankManager = false;
            }
        } catch (NullPointerException npe) {
            hasBankManager = false;
        }
        bank = bankRepository.save(bank);
        if (hasBankManager) {
            bankManager.setBank(bank);
            bankManagerRepository.save(bankManager);
        }
        return bank;
    }

    @Override
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    @Override
    public List<BankManager> getBankManagers() {
        return bankManagerRepository.findAll();
    }

    @Override
    public Bank getBank(Integer id) {
        return bankRepository.findById(id).get();
    }

    @Override
    public List<Customer> getUnauthorizedCustomers() {
        return customerRepository.findCustomerByStatus(Status.UNAUTHORIZED);
    }

    @Override
    public Customer authorizeCustomer(Integer customerId) {

        Customer customer = customerRepository.findById(customerId).get();
        User user = customer.getUser();
        BankBranch bankBranch = customer.getBankBranch();

        Bank bank = bankBranch.getBank();
        List<BankProduct> products = bankProductRepository.findByBank(bank);
        List<BankAccount> bankAccounts = new ArrayList<>();

        if (user.getStatus() == Status.UNAUTHORIZED) {
            user.setStatus(Status.ENABLED);
            customer.setUser(user);
        }

        customer.setStatus(Status.AUTHORIZED);
        customer = customerRepository.save(customer);

        for (BankProduct p: products){
            bankAccountRepository.save(BankAccount.builder()
                    .bankProduct(p)
                    .customer(customer)
                    // TODO Generate an Iban
                    .iban(String.format("IT000000%d%d",p.getId(),customer.getId()))
                    .balance( (float) 0 )
                    .build());
        }
        return customerRepository.save(customer);

    }
}
