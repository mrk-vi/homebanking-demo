package uni.mirkoz.homebankingdemo.repository;

import org.springframework.data.repository.CrudRepository;
import uni.mirkoz.homebankingdemo.model.users.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
