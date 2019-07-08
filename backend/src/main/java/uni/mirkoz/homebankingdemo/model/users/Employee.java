package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;

import javax.persistence.*;
import java.util.List;

@Data@Entity
public class Employee {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(optional = false)
    private BankBranch bankBranch;
    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<BankingOperation> bankingOperations;
    @OneToOne(optional = false)
    private User user;
}
