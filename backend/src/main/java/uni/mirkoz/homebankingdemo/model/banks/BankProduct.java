package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Type type;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Float interestRate;

    public enum Type {
        ACCT,
        PREPAID
    }
}

