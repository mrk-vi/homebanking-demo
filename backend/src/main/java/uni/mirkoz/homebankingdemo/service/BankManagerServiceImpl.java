package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.banks.BankBranchRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankProductRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankServiceRepository;
import uni.mirkoz.homebankingdemo.repository.users.BankManagerRepository;
import uni.mirkoz.homebankingdemo.repository.users.EmployeeRepository;
import uni.mirkoz.homebankingdemo.repository.users.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class BankManagerServiceImpl implements BankManagerService {

    private BankRepository bankRepository;
    private BankManagerRepository bankManagerRepository;
    private BankBranchRepository bankBranchRepository;
    private BankProductRepository bankProductRepository;
    private BankServiceRepository bankServiceRepository;
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;

    public BankManagerServiceImpl(BankRepository bankRepository, BankManagerRepository bankManagerRepository, BankBranchRepository bankBranchRepository, BankProductRepository bankProductRepository, BankServiceRepository bankServiceRepository, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.bankRepository = bankRepository;
        this.bankManagerRepository = bankManagerRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.bankProductRepository = bankProductRepository;
        this.bankServiceRepository = bankServiceRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankBranch createBankBranch(Bank bank, BankBranch bankBranch) {
        bankBranch.setBank(bank);
        return bankBranchRepository.save(bankBranch);
    }

    @Override
    public Employee assignEmployee(BankBranch bankBranch, Employee employee) {
        try {
            employee = employeeRepository.findEmployeeByUser_UsernameOrUser_Mail(
                    employee.getUser().getUsername(),
                    employee.getUser().getMail()
            ).get();

        } catch (NoSuchElementException nsee) {
            // TODO generate a password
        }

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
    public Bank getBank(Integer id) {
        return bankRepository.findById(id)
                .get();
    }

    @Override@Transactional
    public List<BankBranch> getBankBranches(Integer id) {
        return getBankManager(id)
                .getBank()
                .getBankBranches();
    }

    @Override@Transactional
    public BankManager getBankManager(Integer id) {
        return bankManagerRepository.findById(id).get();
    }

    @Override
    public List<BankProduct> getBankProducts(Integer id) {
        return getBankManager(id)
                .getBank()
                .getBankProducts();
    }

    @Override
    public List<BankService> getBankServices(Integer id) {
        return getBankManager(id)
                .getBank()
                .getBankServices();
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
