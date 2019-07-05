package uni.mirkoz.homebankingdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.UserRepository;

import java.io.Serializable;

@RestController
public class Home {

    @GetMapping("/")
    public Iterable<User> home(){
        User user = new User();
        user.setMail("wawawda");
        userRepository.save(user);
        return userRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;
}
