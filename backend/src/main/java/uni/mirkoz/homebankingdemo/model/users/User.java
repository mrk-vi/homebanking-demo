package uni.mirkoz.homebankingdemo.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String mail;
    private String password;
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToOne
    private Administrator admin;
    @OneToOne
    private Employee employee;
    @OneToOne
    private BankManager bankManager;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Customer> customers;
}