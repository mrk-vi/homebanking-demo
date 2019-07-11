package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankServiceOperation;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.List;

public interface ServiceOperationRepository extends JpaRepository<BankServiceOperation, Integer> {
    List<BankServiceOperation> findByBankAccount_Customer_User(User user);
}
