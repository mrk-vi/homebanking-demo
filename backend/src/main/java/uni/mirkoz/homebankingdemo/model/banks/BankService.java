package uni.mirkoz.homebankingdemo.model.banks;

import lombok.Data;

import javax.persistence.*;

@Data@Entity
public class BankService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.ORDINAL)
    private Service service;
    @ManyToOne
    private Bank bank;

    enum Service {
        REFILL,
        CAR_TAX
    }
}
