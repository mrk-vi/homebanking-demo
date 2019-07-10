package uni.mirkoz.homebankingdemo.controller.ws.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.service.contract.VisitorService;

import java.util.List;

@Controller
@RequestMapping("/visitor")
@ResponseBody
public class VisitorController {

    private VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping(value = "banks", produces = "application/json")
    public List<Bank> getBanks() {
        return visitorService.getBanks();
    }
}
