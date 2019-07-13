package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data@Entity
@EqualsAndHashCode(callSuper = true)
public class BankingOperation extends Operation {

    @ManyToOne
    private Employee employee;

    @Column
    private String recipientIban;
}
