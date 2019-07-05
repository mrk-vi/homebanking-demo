package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;
import org.hibernate.annotations.NaturalId;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import javax.persistence.*;
import java.util.List;

@Data@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private BankProduct bankProduct;
    @OneToMany
    @JoinColumn(name = "bank_account_id")
    private List<BankingOperation> bankingOperations;
    @OneToMany
    @JoinColumn(name = "bank_account_id")
    private List<ServiceOperation> serviceOperations;
    @NaturalId
    private String iban;
    @Column
    private Float balance;
}
