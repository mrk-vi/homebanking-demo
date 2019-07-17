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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import uni.mirkoz.homebankingdemo.security.Authority;
import uni.mirkoz.homebankingdemo.security.HomeBankingUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
                .cors().configurationSource(this::corsConfiguration)
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/customer/**").hasAuthority(Authority.CUSTOMER.name())
                .antMatchers("/admin/**").hasAuthority(Authority.ADMIN.name())
                .antMatchers("/manager/**").hasAnyAuthority(Authority.MANAGER.name())
                .antMatchers("/employee/**").hasAuthority(Authority.EMPLOYEE.name())
                .and()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable(); // for h2-console
    }

    private CorsConfiguration corsConfiguration(HttpServletRequest req) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(req.getHeader("origin"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization","origin", "content-type", "accept"));
        configuration.setMaxAge(Integer.toUnsignedLong(3600));
        return configuration;
    }

    private HomeBankingUserDetailsService homeBankingUserDetailsService;

    @Autowired
    public void setHomeBankingUserDetailsService(HomeBankingUserDetailsService homeBankingUserDetailsService) {
        this.homeBankingUserDetailsService = homeBankingUserDetailsService;
    }
}
