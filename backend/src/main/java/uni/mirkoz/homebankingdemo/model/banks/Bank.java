package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

import javax.persistence.*;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"bankBranches", "bankManagers", "images"})
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String address;

    @Column
    private String description;

    @OneToMany(mappedBy = "bank")
    @JsonManagedReference
    private List<BankBranch> bankBranches;

//    @OneToMany(mappedBy = "bank")
//    @JsonManagedReference
//    private List<BankProduct> bankProducts;
//
//    @OneToMany(mappedBy = "bank")
//    @JsonManagedReference
//    private List<BankService> bankServices;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<BankManager> bankManagers;

    @OneToMany(mappedBy = "bank", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Image> images;


}
