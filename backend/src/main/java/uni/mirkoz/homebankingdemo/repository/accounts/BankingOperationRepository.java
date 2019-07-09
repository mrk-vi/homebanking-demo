package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

public interface BankingOperationRepository extends JpaRepository<BankingOperation, Integer> {
}
