package uni.mirkoz.homebankingdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.banks.BankBranchRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankProductRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankServiceRepository;
import uni.mirkoz.homebankingdemo.repository.users.BankManagerRepository;
import uni.mirkoz.homebankingdemo.repository.users.EmployeeRepository;
import uni.mirkoz.homebankingdemo.service.contract.BankManagerService;
import uni.mirkoz.homebankingdemo.util.PasswordGenerator;

import java.util.List;

@Component
public class BankManagerServiceImpl implements BankManagerService {

    private Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    private BankManagerRepository bankManagerRepository;
    private BankBranchRepository bankBranchRepository;
    private BankProductRepository bankProductRepository;
    private BankServiceRepository bankServiceRepository;
    private EmployeeRepository employeeRepository;
    private PasswordGenerator passwordGenerator;
    private PasswordEncoder passwordEncoder;

    public BankManagerServiceImpl(BankManagerRepository bankManagerRepository, BankBranchRepository bankBranchRepository, BankProductRepository bankProductRepository, BankServiceRepository bankServiceRepository, EmployeeRepository employeeRepository, PasswordGenerator passwordGenerator, PasswordEncoder passwordEncoder) {
        this.bankManagerRepository = bankManagerRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.bankProductRepository = bankProductRepository;
        this.bankServiceRepository = bankServiceRepository;
        this.employeeRepository = employeeRepository;
        this.passwordGenerator = passwordGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public BankBranch createBankBranch(Bank bank, BankBranch bankBranch) {
        bankBranch.setBank(bank);
        return bankBranchRepository.save(bankBranch);
    }

    @Override
    public Employee assignEmployee(BankBranch bankBranch, Employee employee) {
        User user = employee.getUser();
        String password = passwordGenerator.newPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ENABLED);
        logger.info(String.format("[new employee credentials in clear] - username: %s - password %s",
                user.getUsername(), password));
        employee.setBankBranch(bankBranch);
        return employeeRepository.save(employee);
    }

    @Override
    public BankProduct createBankProduct(Bank bank, BankProduct bankProduct) {
        bankProduct.setBank(bank);
        return bankProductRepository.save(bankProduct);
    }

    @Override
    public BankService createBankService(Bank bank, BankService bankService) {
        bankService.setBank(bank);
        return bankServiceRepository.save(bankService);
    }

    @Override
    public BankBranch getBankBranch(Integer id) {
        return bankBranchRepository.findById(id)
                .get();
    }

    @Override
    public List<BankBranch> getBankBranches(Integer id) {
        Bank bank = getBankManager(id).getBank();
        return bankBranchRepository.findByBank(bank);
    }

    @Override
    public BankManager getBankManager(Integer id) {
        return bankManagerRepository.findById(id)
                .filter(bankManager -> bankManager.getStatus()== Status.AUTHORIZED).get();
    }

    @Override
    public List<BankProduct> getBankProducts(Integer id) {
        Bank bank = getBankManager(id).getBank();
        return bankProductRepository.findByBank(bank);
    }

    @Override
    public List<BankService> getBankServices(Integer id) {
        Bank bank = getBankManager(id).getBank();
        return bankServiceRepository.findByBank(bank);
    }

    @Override
    public BankProduct updateInterestRate(BankProduct bankProduct, Float interestRate) {
        bankProduct.setInterestRate(interestRate);
        return bankProductRepository.save(bankProduct);
    }

    @Override
    public BankProduct getBankProduct(Integer id) {
        return bankProductRepository.findById(id).get();
    }
}
