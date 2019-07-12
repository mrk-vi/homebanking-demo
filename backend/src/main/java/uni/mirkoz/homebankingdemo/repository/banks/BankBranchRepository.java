package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;

import java.util.List;

public interface BankBranchRepository extends JpaRepository<BankBranch, Integer> {
    List<BankBranch> findByBank(Bank bank);
}
