package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.banks.Bank;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data@Entity
@EqualsAndHashCode(callSuper = true)
public class BankManager extends User {
    @OneToOne
    private Bank bank;
}
