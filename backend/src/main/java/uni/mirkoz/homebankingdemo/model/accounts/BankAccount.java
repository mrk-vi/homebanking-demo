package uni.mirkoz.homebankingdemo.model.accounts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import javax.persistence.*;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    private BankProduct bankProduct;

    @OneToMany(mappedBy = "bankAccount")
    @JsonBackReference
    private List<BankingOperation> bankingOperations;

    @OneToMany(mappedBy = "bankAccount")
    @JsonBackReference
    private List<BankServiceOperation> bankServiceOperations;

    @NaturalId
    private String iban;

    @Column
    private Float balance;
}
