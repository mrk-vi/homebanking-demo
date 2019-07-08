package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.banks.Bank;

import javax.persistence.*;

@Data@Entity
public class BankManager {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private Bank bank;
    @OneToOne(optional = false)
    private User user;
}
