package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankService;

import java.util.List;

public interface BankServiceRepository extends JpaRepository<BankService, Integer> {
    List<BankService> findByBank(Bank bank);

    List<BankService> findByBank_Id(Integer bankId);
}

