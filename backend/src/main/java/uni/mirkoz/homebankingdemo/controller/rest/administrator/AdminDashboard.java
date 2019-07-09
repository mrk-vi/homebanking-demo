package uni.mirkoz.homebankingdemo.controller.rest.administrator;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.AdministratorService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminDashboard {

    private AdministratorService administratorService;

    public AdminDashboard(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(value = "banks", produces = "application/json")
    public List<Bank> getBanks() {
        return administratorService.getBanks();
    }

    @GetMapping(value = "bank/{id}", produces = "application/json")
    public Bank getBank(@PathVariable Integer id) {
        return administratorService.getBank(id);
    }

    @PostMapping(value = "bank", produces = "application/json", consumes = "application/json")
    public Bank createBank(@RequestBody CreateBankForm form) {
        Bank bank = Bank.builder()
                .name(form.getBankName())
                .address(form.getBankAddress())
                .description(form.getBankDescription())
                .build();
        User user = User.builder()
                .mail(form.getMail())
                .username(form.getUsername())
                .build();
        BankManager bankManager = BankManager.builder()
                .user(user)
                .build();
        return administratorService.saveBank(bank, bankManager);
    }

    @PutMapping(value = "bank/{id}/bankManager", produces = "application/json", consumes = "application/json")
    public Bank assignBankManager(@RequestBody AssignBankManagerForm form, @PathVariable Integer id) {
            Bank bank = administratorService.getBank(id);
            User user = User.builder()
                    .mail(form.getMail())
                    .username(form.getUsername())
                    .build();
            BankManager bankManager = BankManager.builder()
                    .user(user)
                    .build();
            return administratorService.assignBankManager(bank, bankManager);
    }

}

@Data
class CreateBankForm {
    private String bankName;
    private String bankAddress;
    private String bankDescription;
    // TODO handling images uploads
    private String username;
    private String mail;
}

@Data
class AssignBankManagerForm {
    private String username;
    private String mail;
}
