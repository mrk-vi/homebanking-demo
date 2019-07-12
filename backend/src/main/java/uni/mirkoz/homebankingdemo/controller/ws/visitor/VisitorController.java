package uni.mirkoz.homebankingdemo.controller.ws.visitor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.controller.ws.visitor.form.RegistrationRequestForm;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.User;
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

    @GetMapping(value = "bank/{id}/bank-products", produces = "application/json")
    public List<BankProduct> getBankProducts(@PathVariable Integer id) {
        return visitorService.getProducts(id);
    }

    @GetMapping(value = "bank/{id}/bank-services", produces = "application/json")
    public List<BankService> getBankService(@PathVariable Integer id) {
        return visitorService.getServices(id);
    }

    @PostMapping(value = "registration-request")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationRequest(@RequestBody RegistrationRequestForm form) {
        visitorService
                .sendRegistrationRequest(Customer
                        .builder()
                        .user(User.builder()
                                .username(form.getUsername())
                                .mail(form.getEmail())
                                .password(form.getPassword())
                                .build())
                .build(), form.getBankBranchId());
    }
}
