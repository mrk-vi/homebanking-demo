package uni.mirkoz.homebankingdemo.model.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Administrator{

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JsonManagedReference
    private User user;
}
