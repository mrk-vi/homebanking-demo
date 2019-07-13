package uni.mirkoz.homebankingdemo.model.accounts;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data@MappedSuperclass
@ToString(exclude = {"bankAccount"})
public abstract class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
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
}

