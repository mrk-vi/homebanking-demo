package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.banks.BankService;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data@Entity
@EqualsAndHashCode(callSuper = true)
public class BankServiceOperation extends Operation{

    @ManyToOne
    private BankService bankService;
}
