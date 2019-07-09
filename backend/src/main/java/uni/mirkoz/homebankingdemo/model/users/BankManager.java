package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.mirkoz.homebankingdemo.model.banks.Bank;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankManager {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
}
