package uni.mirkoz.homebankingdemo.controller.ws.authentication;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uni.mirkoz.homebankingdemo.model.users.UserInfo;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.security.Authority;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auth")
@ResponseBody
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "user-info", produces = "application/json")
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        User user = authenticationService.getPrincipal().getUser();
        List<Authority> authorities = new ArrayList<>();

        for (GrantedAuthority ga : authenticationService.getPrincipal().getAuthorities()) {
            authorities.add(Authority.valueOf(ga.getAuthority()));
        }

        userInfo.setUser(user);
        userInfo.setAuthorities(authorities);

        return userInfo;
    }
}