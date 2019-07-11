package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.List;

public interface BankingOperationRepository extends JpaRepository<BankingOperation, Integer> {
    List<BankingOperation> findByBankAccount_Customer_User(User user);
}
