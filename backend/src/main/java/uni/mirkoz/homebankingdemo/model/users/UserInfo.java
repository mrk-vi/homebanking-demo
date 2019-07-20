package uni.mirkoz.homebankingdemo.model.users;

import lombok.Data;
import uni.mirkoz.homebankingdemo.security.Authority;

import java.util.List;

@Data
public class UserInfo {

    private User user;
    private List<Authority> authorities;
}
