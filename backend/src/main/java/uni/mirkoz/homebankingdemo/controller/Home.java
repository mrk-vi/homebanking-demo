package uni.mirkoz.homebankingdemo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class Home {
    @GetMapping(value = "/")
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No resource at this path")
    public void root(){
    }
}
