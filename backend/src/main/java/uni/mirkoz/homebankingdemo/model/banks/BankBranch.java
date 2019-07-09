package uni.mirkoz.homebankingdemo.model.banks;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BankBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JsonManagedReference
    private Bank bank;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String address;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column
    @ApiModelProperty(dataType = "java.lang.String", example = "9:00")
    private Time opening;

    @JsonFormat(pattern = "HH:mm")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    @Column
    @ApiModelProperty(dataType = "java.lang.String", example = "19:00")
    private Time closing;

    @OneToMany(mappedBy = "bankBranch", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Employee> employees;

    @OneToMany(mappedBy = "bankBranch", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Customer> customers;

    @OneToMany(mappedBy = "bankBranch", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Image> images;

}
