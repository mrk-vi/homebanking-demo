package uni.mirkoz.homebankingdemo.controller.rest.bankmanager;

import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.controller.rest.bankmanager.form.*;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.BankManagerService;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class BankManagerDashboard {

    private BankManagerService bankManagerService;

    public BankManagerDashboard(BankManagerService bankManagerService) {
        this.bankManagerService = bankManagerService;
    }

    @GetMapping(value = "bank-branch/{id}", produces = "application/json")
    public BankBranch getBankBranch(@PathVariable Integer id){
        return bankManagerService.getBankBranch(id);
    }

    //TODO retrieve banches from bankmanager details
    @GetMapping(value = "{id}/bank-branches", produces = "application/json")
    public List<BankBranch> getBankBranches(@PathVariable Integer id){
        return bankManagerService.getBankBranches(id);
    }

    //TODO retrieve banches from bankmanager details
    @GetMapping(value = "{id}/bank-products", produces = "application/json")
    public List<BankProduct> getBankProducts(@PathVariable Integer id){
        return bankManagerService.getBankProducts(id);
    }

    //TODO retrieve banches from bankmanager details
    @GetMapping(value = "{id}/bank-services", produces = "application/json")
    public List<BankService> getBankServices(@PathVariable Integer id){
        return bankManagerService.getBankServices(id);
    }


    //TODO retrieve bank from bankmanager details
    @PostMapping(value = "bank/{id}/bank-branch", consumes = "application/json", produces = "application/json")
    public BankBranch createBankBranch(@PathVariable Integer id, @RequestBody CreateBankBranchForm form){
        return bankManagerService.createBankBranch(
                bankManagerService.getBank(id),
                BankBranch.builder()
                    .name(form.getBankBranchName())
                    .address(form.getBankBranchAddress())
                    .opening(form.getBankBranchOpening())
                    .closing(form.getBankBranchClosing())
                    .build()
        );
    }

    //TODO retrieve bank from bankmanager details
    @PutMapping(value = "bank-branch/{id}/employee", consumes = "application/json", produces = "application/json")
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

    //TODO retrieve bank from bankmanager details
    @PutMapping(value = "bank-product/{id}/interest-rate", consumes = "application/json", produces = "application/json")
    public BankProduct setInterestRate(@PathVariable Integer id, @RequestBody InterestRateForm form){
        return bankManagerService.updateInterestRate(
                bankManagerService.getBankProduct(id),
                form.getInterestRate()
        );
    }


    //TODO retrieve bank from bankmanager details
    @PostMapping(value = "bank/{id}/bank-product", consumes = "application/json", produces = "application/json")
    public BankProduct createBankProduct(@PathVariable Integer id, @RequestBody CreateBankProductForm form){
        return bankManagerService.createBankProduct(
                bankManagerService.getBank(id),
                BankProduct.builder()
                        .name(form.getName())
                        .description(form.getDescription())
                        .interestRate(form.getInterestRate())
                        .type(form.getType())
                        .build()
        );
    }

    //TODO retrieve bank from bankmanager details
    @PostMapping(value = "bank/{id}/bank-service", consumes = "application/json", produces = "application/json")
    public BankService createBankService(@PathVariable Integer id, @RequestBody CreateBankServiceForm form){
        return bankManagerService.createBankService(
                bankManagerService.getBank(id),
                BankService.builder()
                        .type(form.getType())
                        .build()
        );
    }


}
