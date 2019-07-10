package uni.mirkoz.homebankingdemo.security;

import org.springframework.security.core.userdetails.UserDetails;
import uni.mirkoz.homebankingdemo.model.users.Administrator;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import java.util.List;
import java.util.Optional;

public interface HomeBankingUserDetails extends UserDetails {

    Optional<Administrator> getAdministrator();
    Optional<BankManager> getBankManager();
    Optional<Employee> getEmployee();
    Optional<List<Customer>> getCustomers();

}
