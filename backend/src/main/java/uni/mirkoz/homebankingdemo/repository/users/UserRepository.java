package uni.mirkoz.homebankingdemo.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.users.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
