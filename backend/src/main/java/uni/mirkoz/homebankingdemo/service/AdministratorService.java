package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

public interface AdministratorService {
    Bank createBank(Bank bank, BankManager bankManager);
    Bank assignBankManager(Bank bank, BankManager bankManager);
}
