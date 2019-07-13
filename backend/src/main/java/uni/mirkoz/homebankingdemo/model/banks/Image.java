package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bank", "bankBranch"})
public class Image {

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne
    @JsonBackReference
    private Bank bank;

    @ManyToOne
    @JsonBackReference
    private BankBranch bankBranch;
}
