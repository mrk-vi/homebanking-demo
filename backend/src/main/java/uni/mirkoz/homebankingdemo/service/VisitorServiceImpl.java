package uni.mirkoz.homebankingdemo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.banks.BankBranchRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankProductRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankServiceRepository;
import uni.mirkoz.homebankingdemo.repository.users.CustomerRepository;
import uni.mirkoz.homebankingdemo.repository.users.UserRepository;
import uni.mirkoz.homebankingdemo.service.contract.VisitorService;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class VisitorServiceImpl implements VisitorService {

    private BankServiceRepository bankServiceRepository;
    private BankProductRepository bankProductRepository;
    private BankRepository bankRepository;
    private BankBranchRepository bankBranchRepository;
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private PasswordEncoder encoder;

    public VisitorServiceImpl(BankServiceRepository bankServiceRepository, BankProductRepository bankProductRepository, BankRepository bankRepository, BankBranchRepository bankBranchRepository, UserRepository userRepository, CustomerRepository customerRepository, PasswordEncoder encoder) {
        this.bankServiceRepository = bankServiceRepository;
        this.bankProductRepository = bankProductRepository;
        this.bankRepository = bankRepository;
        this.bankBranchRepository = bankBranchRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.encoder = encoder;
    }

    @Override
    public List<BankService> getServices(Integer bankId) {
        return bankServiceRepository.findByBank_Id(bankId);
    }

    @Override
    public List<BankProduct> getProducts(Integer bankId) {
        return bankProductRepository.findByBank_id(bankId);
    }

    @Override
    public List<Bank> getBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Customer sendRegistrationRequest(Customer registrationRequest, Integer bankBranchId) {
        try{
            User user = userRepository.findByUsername(registrationRequest.getUser().getUsername()).get();
            registrationRequest.setUser(user);
        } catch (NoSuchElementException nsee) {
            User newUser = registrationRequest.getUser();
            String encoded = encoder.encode(newUser.getPassword());
            newUser.setPassword(encoded);
            newUser.setStatus(Status.UNAUTHORIZED);
            userRepository.save(newUser);
        }
        registrationRequest.setStatus(Status.UNAUTHORIZED);
        BankBranch bankBranch = bankBranchRepository.findById(bankBranchId).get();
        registrationRequest.setBankBranch(bankBranch);
        return customerRepository.save(registrationRequest);
    }
}

