package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data@Entity
@EqualsAndHashCode(callSuper = true)
public class Administrator extends User {
}
