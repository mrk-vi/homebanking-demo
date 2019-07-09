package uni.mirkoz.homebankingdemo.model.banks;

import lombok.Data;

import javax.persistence.*;

@Data@Entity
public class BankPhoto {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Bank bank;
    @Column(nullable = false)
    private String path;
}
