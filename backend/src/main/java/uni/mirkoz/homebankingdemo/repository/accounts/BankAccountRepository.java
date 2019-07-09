package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
