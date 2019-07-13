package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import uni.mirkoz.homebankingdemo.security.HomeBankingUserPrincipal;

@Service
public interface AuthenticationService {

    Authentication getAuthentication();

    HomeBankingUserPrincipal getPrincipal();
}
