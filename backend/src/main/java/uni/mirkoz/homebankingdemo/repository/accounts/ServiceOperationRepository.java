package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.accounts.BankServiceOperation;

public interface ServiceOperationRepository extends JpaRepository<BankServiceOperation, Integer> {
}
