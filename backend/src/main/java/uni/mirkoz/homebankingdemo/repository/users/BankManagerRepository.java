package uni.mirkoz.homebankingdemo.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.Optional;

public interface BankManagerRepository extends JpaRepository<BankManager, Integer> {
    Optional<BankManager> findBankManagerByUser(User user);
    Optional<BankManager> findBankManagerByUser_UsernameOrUser_Mail(String username, String mail);
    Optional<BankManager> findBankManagerByBankAndStatus(Bank bank, Status status);
}
