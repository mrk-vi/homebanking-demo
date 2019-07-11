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

    @Enumerated(value = EnumType.ORDINAL)
    private Status status;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Administrator administrator;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private BankManager bankManager;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Employee employee;

//    @JsonIgnore
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    private List<Customer> customers;

}