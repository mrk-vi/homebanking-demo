package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data@Entity
@EqualsAndHashCode(callSuper = true)
public class Employee extends User {
    @ManyToOne
    private BankBranch bankBranch;
    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<BankingOperation> bankingOperations;
}
