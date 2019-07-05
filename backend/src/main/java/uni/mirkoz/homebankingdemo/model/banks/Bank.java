package uni.mirkoz.homebankingdemo.model.banks;

import lombok.Data;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

import javax.persistence.*;
import java.util.List;

@Data@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private BankManager bankManager;
    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<BankBranch> bankBranches;
    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<BankProduct> bankProducts;
    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<BankService> bankServices;
}
