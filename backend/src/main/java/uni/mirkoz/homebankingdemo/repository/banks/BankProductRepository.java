package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;

import java.util.List;

public interface BankProductRepository extends JpaRepository<BankProduct, Integer> {

    List<BankProduct> findBankProductByBank(Bank bank);
}
