package uni.mirkoz.homebankingdemo.model.banks;

import lombok.Data;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import javax.persistence.*;
import java.util.List;

@Data@Entity
public class BankBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Bank bank;
    @OneToMany
    @JoinColumn(name = "bank_branch_id")
    private List<Employee> employees;
    @OneToMany
    @JoinColumn(name = "bank_branch_id")
    private List<Customer> customers;
}
