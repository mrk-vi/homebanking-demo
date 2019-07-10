package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import uni.mirkoz.homebankingdemo.model.banks.Bank;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "bank"})
public class BankManager {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
}
