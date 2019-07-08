package uni.mirkoz.homebankingdemo.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.users.BankManager;

public interface BankManagerRepository extends JpaRepository<BankManager, Integer> {
}
