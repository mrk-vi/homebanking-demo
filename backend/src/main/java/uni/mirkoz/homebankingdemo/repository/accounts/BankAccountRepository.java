package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    List<BankAccount> findBankAccountsByCustomer_User_Username(String username);
    List<BankAccount> findBankAccountsByCustomer_User(User user);
    BankAccount findBankAccountsByIdAndCustomer_User(Integer id, User user);
    BankAccount findBankAccountsByIban(String iban);
}
