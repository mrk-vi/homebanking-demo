package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String mail;
    private String password;
    @Column
    @Temporal(TemporalType.DATE)
    private Date createTime;
}