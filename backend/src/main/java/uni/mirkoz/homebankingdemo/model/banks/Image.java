package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data@Entity
@ToString(exclude = {"bank", "bankBranch"})
public class Image {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    @ManyToOne
    @JsonManagedReference
    private BankBranch bankBranch;
}
