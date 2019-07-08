package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;

public interface BankProductRepository extends JpaRepository<BankProduct, Integer> {
}
