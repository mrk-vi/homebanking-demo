package uni.mirkoz.homebankingdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uni.mirkoz.homebankingdemo.model.users.Administrator;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.users.AdministratorRepository;
import uni.mirkoz.homebankingdemo.repository.users.UserRepository;

@RestController
public class Home {
    @GetMapping(value = "/")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No resource at this path")
    public void root(){
    }

    @GetMapping("/test")
    public Iterable<Administrator> test(){
        return administratorRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
}
