package uni.mirkoz.homebankingdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uni.mirkoz.homebankingdemo.model.users.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class HomeBankingUserPrincipal implements HomeBankingUserDetails {

    private User user;

    public HomeBankingUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(Authority.USER.name()));

        if (getAdministrator().isPresent())
            authorities.add( new SimpleGrantedAuthority(Authority.ADMIN.name()) );
        if (getBankManager().isPresent())
            authorities.add( new SimpleGrantedAuthority(Authority.MANAGER.name()) );
        if (getEmployee().isPresent())
            authorities.add( new SimpleGrantedAuthority(Authority.EMPLOYEE.name()) );
//        if (getCustomers().isPresent() && ! getCustomers().get().isEmpty())
//            authorities.add( new SimpleGrantedAuthority("customer") );
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getStatus() != Status.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() != Status.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getStatus() != Status.EXPIRED;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == Status.ENABLED;
    }

    @Override
    public Optional<Administrator> getAdministrator() {
        return Optional.ofNullable(user.getAdministrator());
    }

    @Override
    public Optional<BankManager> getBankManager() {
        return Optional.ofNullable(user.getBankManager());
    }

    @Override
    public Optional<Employee> getEmployee() {
        return Optional.ofNullable(user.getEmployee());
    }

    @Override
    public User getUser() {
        return this.user;
    }
//
//    @Override
//    public Optional<List<Customer>> getCustomers() {
//        return Optional.ofNullable(user.getCustomers());
//    }
}
