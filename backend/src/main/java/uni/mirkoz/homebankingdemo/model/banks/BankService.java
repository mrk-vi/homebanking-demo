package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data@Builder
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"type", "bank_id"})
})
@NoArgsConstructor
@AllArgsConstructor
public class BankService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Type type;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    public enum Type {
        REFILL,
        CAR_TAX
    }
}


