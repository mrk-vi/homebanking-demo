package uni.mirkoz.homebankingdemo.model.users;

import lombok.*;
import net.bytebuddy.description.field.FieldDescription;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Administrator{

    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(optional = false)
    private User user;
}
