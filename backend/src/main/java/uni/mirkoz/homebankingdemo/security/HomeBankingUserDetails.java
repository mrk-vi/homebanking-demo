package uni.mirkoz.homebankingdemo.security;

import org.springframework.security.core.userdetails.UserDetails;
import uni.mirkoz.homebankingdemo.model.users.Administrator;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.Optional;

public interface HomeBankingUserDetails extends UserDetails {

    Optional<Administrator> getAdministrator();
    Optional<BankManager> getBankManager();
    Optional<Employee> getEmployee();
    User getUser();
//    Optional<List<Customer>> getCustomers();

}
