package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;

import javax.persistence.*;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(optional = false)
    private BankBranch bankBranch;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<BankAccount> bankAccounts;

    @ManyToOne(optional = false)
    @JsonManagedReference
    private User user;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
}
