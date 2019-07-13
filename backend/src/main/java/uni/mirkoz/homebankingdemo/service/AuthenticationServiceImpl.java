package uni.mirkoz.homebankingdemo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.security.HomeBankingUserPrincipal;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;

@Component
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public HomeBankingUserPrincipal getPrincipal() {
        return (HomeBankingUserPrincipal) getAuthentication().getPrincipal();
    }
}
