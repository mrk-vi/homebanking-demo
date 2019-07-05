package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;

import javax.persistence.*;

@Data@MappedSuperclass
public abstract class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private BankAccount bankAccount;
    @Enumerated(EnumType.STRING)
    private OperationState operationState;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Column
    private Float amount;
}
