package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Service;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

import java.util.List;

@Service
public interface AdministratorService {
    Bank saveBank(Bank bank, BankManager bankManager);
    Bank assignBankManager(Bank bank, BankManager bankManager);
    Bank removeBankManager(Bank bank);
    List<Bank> getBanks();
    Bank getBank(Integer id);
}
