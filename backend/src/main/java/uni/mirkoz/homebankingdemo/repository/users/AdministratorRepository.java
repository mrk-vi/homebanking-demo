package uni.mirkoz.homebankingdemo.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.users.Administrator;
import uni.mirkoz.homebankingdemo.model.users.User;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
    Administrator findAdministratorByUser(User user);

}
