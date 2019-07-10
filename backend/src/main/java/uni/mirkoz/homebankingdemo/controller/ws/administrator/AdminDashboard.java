package uni.mirkoz.homebankingdemo.controller.ws.administrator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.controller.ws.administrator.form.AssignBankManagerForm;
import uni.mirkoz.homebankingdemo.controller.ws.administrator.form.CreateBankForm;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.contract.AdministratorService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@ResponseBody
public class AdminDashboard {

    private AdministratorService administratorService;

    public AdminDashboard(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping(value = "banks", produces = "application/json")
    public List<Bank> getBanks() {
        return administratorService.getBanks();
    }

    @GetMapping(value = "managers", produces = "application/json")
    public List<BankManager> getBankManagers() {
        return administratorService.getBankManagers();
    }

    @GetMapping(value = "bank/{id}", produces = "application/json")
    public Bank getBank(@PathVariable Integer id) {
        return administratorService.getBank(id);
    }

    @PostMapping(value = "bank", produces = "application/json", consumes = "application/json")
    public Bank createBank(@RequestBody CreateBankForm form) {
        return administratorService.saveBank(
                Bank.builder()
                        .name(form.getBankName())
                        .address(form.getBankAddress())
                        .description(form.getBankDescription())
                        .build(),
                BankManager.builder()
                        .user(User.builder()
                                .username(form.getUsername())
                                .mail(form.getMail())
                                .build())
                        .build()
        );
    }

    @PutMapping(value = "bank/{id}/bank-manager", produces = "application/json", consumes = "application/json")
    public Bank assignBankManager(@RequestBody AssignBankManagerForm form, @PathVariable Integer id) {
            return administratorService.assignBankManager(
                    administratorService.getBank(id),
                    BankManager.builder()
                            .user(User.builder()
                                    .mail(form.getMail())
                                    .username(form.getUsername())
                                    .build())
                            .build()
            );
    }

}

