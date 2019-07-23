package uni.mirkoz.homebankingdemo.controller.ws.bankmanager;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.controller.ws.bankmanager.form.*;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;
import uni.mirkoz.homebankingdemo.service.contract.BankManagerService;

import java.util.List;

@Controller
@RequestMapping("/manager")
@ResponseBody
public class BankManagerDashboard {

    private BankManagerService bankManagerService;
    private AuthenticationService authenticationService;

    public BankManagerDashboard(BankManagerService bankManagerService, AuthenticationService authenticationService) {
        this.bankManagerService = bankManagerService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "bank-branch/{id}", produces = "application/json")
    public BankBranch getBankBranch(@PathVariable Integer id){
        return bankManagerService.getBankBranch(id);
    }

    @GetMapping(value = "bank-branches", produces = "application/json")
    public List<BankBranch> getBankBranches(){
        Integer id = authenticationService.getPrincipal().getBankManager().get().getId();
        return bankManagerService.getBankBranches(id);

    }

    @GetMapping(value = "bank-products", produces = "application/json")
    public List<BankProduct> getBankProductsByManager(){
        Integer id = authenticationService.getPrincipal().getBankManager().get().getId();
        return bankManagerService.getBankProducts(id);
    }

    @GetMapping(value = "bank-services", produces = "application/json")
    public List<BankService> getBankServicesByManager(){
        Integer id = authenticationService.getPrincipal().getBankManager().get().getId();
        return bankManagerService.getBankServices(id);
    }


    @PostMapping(value = "bank-branch", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BankBranch createBankBranch(@RequestBody CreateBankBranchForm form){
        Bank bank = authenticationService.getPrincipal().getBankManager().get().getBank();
        return bankManagerService.createBankBranch(
                bank,
                BankBranch.builder()
                    .name(form.getBankBranchName())
                    .address(form.getBankBranchAddress())
                    .opening(form.getBankBranchOpening())
                    .closing(form.getBankBranchClosing())
                    .build()
        );
    }

    @PostMapping(value = "bank-product", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BankProduct createBankProduct(@RequestBody CreateBankProductForm form){
        Bank bank = authenticationService.getPrincipal().getBankManager().get().getBank();
        return bankManagerService.createBankProduct(
                bank,
                BankProduct.builder()
                        .name(form.getName())
                        .description(form.getDescription())
                        .interestRate(form.getInterestRate())
                        .type(form.getType())
                        .build()
        );
    }

    @PostMapping(value = "bank-service", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BankService createBankService(@RequestBody CreateBankServiceForm form){
        Bank bank = authenticationService.getPrincipal().getBankManager().get().getBank();
        return bankManagerService.createBankService(
                bank,
                BankService.builder()
                        .type(form.getType())
                        .build()
        );
    }


    @PostMapping(value = "bank-branch/{id}/employee", consumes = "application/json", produces = "application/json")
    public Employee assignEmployee(@PathVariable Integer id, @RequestBody AssignEmployeeForm form){
        return bankManagerService.assignEmployee(
                bankManagerService.getBankBranch(id),
                Employee.builder()
                        .user(User.builder()
                                .username(form.getUsername())
                                .mail(form.getMail())
                                .build())
                        .build()
        );
    }

    @PutMapping(value = "bank-product/{id}/interest-rate", consumes = "application/json", produces = "application/json")
    public BankProduct setInterestRate(@PathVariable Integer id, @RequestBody InterestRateForm form){
        return bankManagerService.updateInterestRate(
                bankManagerService.getBankProduct(id),
                form.getInterestRate()
        );
    }
}
