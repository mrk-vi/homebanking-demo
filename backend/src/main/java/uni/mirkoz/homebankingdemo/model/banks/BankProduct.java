package uni.mirkoz.homebankingdemo.model.banks;

import lombok.Data;

import javax.persistence.*;

@Data@Entity
public class BankProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    private Product product;
    @ManyToOne
    private Bank bank;

    enum Product {
        ACCT,
        PREPAID
    }
}
