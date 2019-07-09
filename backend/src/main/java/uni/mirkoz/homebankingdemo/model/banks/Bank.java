package uni.mirkoz.homebankingdemo.model.banks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

import javax.persistence.*;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "bank_manager_id")
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
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String address;
    @Column
    private String description;
    @OneToMany
    @JoinColumn(name = "bank_id")
    private List<BankPhoto> bankPhotos;
}
