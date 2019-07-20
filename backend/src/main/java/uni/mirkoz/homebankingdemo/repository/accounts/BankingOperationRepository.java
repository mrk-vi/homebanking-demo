package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationState;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.List;

public interface BankingOperationRepository extends JpaRepository<BankingOperation, Integer>, JpaSpecificationExecutor<BankingOperation> {

    List<BankingOperation> findByBankAccount_Customer_User(User user);
    List<BankingOperation> findByBankAccount_Customer_BankBranch(BankBranch bankBranch);
    BankingOperation findByIdAndOperationStateAndBankAccount_Customer_BankBranch(Integer bankOperationId, OperationState open, BankBranch bankBranch);
}
