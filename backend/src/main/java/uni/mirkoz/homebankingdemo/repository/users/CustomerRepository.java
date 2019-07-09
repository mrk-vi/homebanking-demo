package uni.mirkoz.homebankingdemo.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.User;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByUser(User user);

}
