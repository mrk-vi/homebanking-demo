package uni.mirkoz.homebankingdemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uni.mirkoz.homebankingdemo.security.HomeBankingUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(homeBankingUserDetailsService);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }


    // Secure endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                    .antMatchers("/admin/**").hasAuthority("admin")
                    .antMatchers("/manager/**").hasAuthority("manager")
                    .antMatchers("/employee/**").hasAuthority("employee")
                    .antMatchers("/customer/**").hasAuthority("customer")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable(); // for h2-console
    }

    private HomeBankingUserDetailsService homeBankingUserDetailsService;

    @Autowired
    public void setHomeBankingUserDetailsService(HomeBankingUserDetailsService homeBankingUserDetailsService) {
        this.homeBankingUserDetailsService = homeBankingUserDetailsService;
    }
}
