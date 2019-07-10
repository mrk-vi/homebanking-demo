package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;

import javax.persistence.*;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
public class Employee {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private BankBranch bankBranch;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<BankingOperation> bankingOperations;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;
}
