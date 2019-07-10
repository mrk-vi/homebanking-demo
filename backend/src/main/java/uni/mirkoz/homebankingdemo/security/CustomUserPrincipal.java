package uni.mirkoz.homebankingdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uni.mirkoz.homebankingdemo.model.users.Status;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserPrincipal implements UserDetails {

    private User user;

    public CustomUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user.getAdministrator() != null)
            authorities.add( new SimpleGrantedAuthority("admin") );
        if (user.getBankManager() != null)
            authorities.add( new SimpleGrantedAuthority("manager") );
        if (user.getEmployee() != null)
            authorities.add( new SimpleGrantedAuthority("employee") );
        if (user.getCustomers() != null && ! user.getCustomers().isEmpty())
            authorities.add( new SimpleGrantedAuthority("customer") );
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
}
