package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.accounts.BankServiceOperation;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

@Service@Transactional
public interface CustomerService {

    BankingOperation deposit(BankAccount bankAccount, BankingOperation bankingOperation);

    BankingOperation whithdraw(BankAccount bankAccount, BankingOperation bankingOperation);

    BankingOperation transfer(BankAccount bankAccount, BankingOperation bankingOperation);

    BankServiceOperation refill(BankAccount bankAccount, BankServiceOperation bankServiceOperation);

    BankServiceOperation carTax(BankAccount bankAccount, BankServiceOperation bankServiceOperation);

    //TODO Informative operations
}
