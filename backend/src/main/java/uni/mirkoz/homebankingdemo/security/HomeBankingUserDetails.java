package uni.mirkoz.homebankingdemo.security;

import org.springframework.security.core.userdetails.UserDetails;
import uni.mirkoz.homebankingdemo.model.users.*;

import java.util.List;
import java.util.Optional;

public interface HomeBankingUserDetails extends UserDetails {

    Optional<Administrator> getAdministrator();
    Optional<BankManager> getBankManager();
    Optional<Employee> getEmployee();
    User getUser();
    Optional<List<Customer>> getCustomers();

}
