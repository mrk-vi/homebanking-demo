package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.banks.BankRepository;
import uni.mirkoz.homebankingdemo.repository.users.BankManagerRepository;
import uni.mirkoz.homebankingdemo.repository.users.UserRepository;

import java.util.List;


@Component
public class AdministratorServiceImpl implements AdministratorService {

    private BankRepository bankRepository;
    private BankManagerRepository bankManagerRepository;
    private UserRepository userRepository;

    public AdministratorServiceImpl(BankRepository bankRepository, BankManagerRepository bankManagerRepository, UserRepository userRepository) {
        this.bankRepository = bankRepository;
        this.bankManagerRepository = bankManagerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Bank assignBankManager(Bank bank, BankManager bankManager) {
        bank = removeBankManager(bank);
        return saveBank(bank, bankManager);
    }

    @Override@Transactional
    public Bank removeBankManager(Bank bank) {
        BankManager previousManager = bank.getBankManager();
        try{
            previousManager.setStatus(Status.CEASED);
            bankManagerRepository.save(previousManager);
        } catch (NullPointerException npe){}
        bank.setBankManager(null);
        return bankRepository.save(bank);
    }

    @Override@Transactional
    public Bank saveBank(Bank bank, BankManager bankManager) {
        boolean isBankManager = true;
        try {
            User user = bankManager.getUser();
            if (!user.getMail().isEmpty() && !user.getUsername().isEmpty()) {
                // TODO generate a password
                user = userRepository.save(user);
                bankManager.setUser(user);
                bankManager.setStatus(Status.AUTHORIZED);
                bankManager = bankManagerRepository.save(bankManager);
                user.setBankManager(bankManager);
                userRepository.save(user);
                bank.setBankManager(bankManager);
            } else {
                isBankManager = false;
            }
        } catch (NullPointerException npe) {
            isBankManager = false;
        }
        bank = bankRepository.save(bank);
        if (isBankManager) {
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
    public Bank getBank(Integer id) {
        return bankRepository.findById(id).get();
    }
}
