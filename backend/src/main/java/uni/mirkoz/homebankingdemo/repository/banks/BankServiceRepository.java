package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.BankService;

public interface BankServiceRepository extends JpaRepository<BankService, Integer> {
}
