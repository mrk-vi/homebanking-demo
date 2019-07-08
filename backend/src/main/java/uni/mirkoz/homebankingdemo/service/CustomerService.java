package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.accounts.BankServiceOperation;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

public interface CustomerService {
    BankingOperation deposit(BankAccount bankAccount, BankingOperation bankingOperation);
    BankingOperation whithdraw(BankAccount bankAccount, BankingOperation bankingOperation);
    BankingOperation transfer(BankAccount bankAccount, BankingOperation bankingOperation);
    BankServiceOperation refill(BankAccount bankAccount, BankServiceOperation bankServiceOperation);
    BankServiceOperation carTax(BankAccount bankAccount, BankServiceOperation bankServiceOperation);

    //TODO Informative operations
}
