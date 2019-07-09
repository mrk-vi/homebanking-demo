package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String mail;
    @Column@JsonIgnore
    private String password;
    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToOne@JsonIgnore
    private Administrator admin;
    @OneToOne@JsonIgnore
    private Employee employee;
    @OneToOne@JsonIgnore
    private BankManager bankManager;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Customer> customers;
}