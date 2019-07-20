package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data@Builder
@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"type", "bank_id"})
})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bank"})
public class BankService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private Type type;

    @ManyToOne
    @JsonBackReference
    private Bank bank;

    public enum Type {
        REFILL,
        CAR_TAX
    }
}


