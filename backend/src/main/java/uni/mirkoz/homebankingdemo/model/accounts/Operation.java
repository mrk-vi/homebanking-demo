package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data@MappedSuperclass
public abstract class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private BankAccount bankAccount;
    @Enumerated(EnumType.ORDINAL)
    private OperationState operationState;
    @Enumerated(EnumType.ORDINAL)
    private OperationType operationType;
    @Column
    private Float amount;
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    enum OperationState {
        OPEN,
        REJECTED,
        AUTHORIZED
    }

    enum OperationType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        SEPA,
        REFILL,
        CAR_TAX
    }
}
