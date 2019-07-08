package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;

public interface BankBranchRepository extends JpaRepository<BankBranch, Integer> {
}
