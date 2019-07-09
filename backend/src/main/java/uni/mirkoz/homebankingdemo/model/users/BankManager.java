package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import uni.mirkoz.homebankingdemo.model.banks.Bank;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankManager {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @OneToOne(optional = false)
    private User user;
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
}
