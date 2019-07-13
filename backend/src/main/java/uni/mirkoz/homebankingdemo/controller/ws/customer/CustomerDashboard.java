package uni.mirkoz.homebankingdemo.controller.ws.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.controller.ws.customer.form.CarTaxForm;
import uni.mirkoz.homebankingdemo.controller.ws.customer.form.OperationForm;
import uni.mirkoz.homebankingdemo.controller.ws.customer.form.RefillForm;
import uni.mirkoz.homebankingdemo.model.accounts.*;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;
import uni.mirkoz.homebankingdemo.service.contract.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
@ResponseBody
public class CustomerDashboard {

    private AuthenticationService authenticationService;
    private CustomerService customerService;

    public CustomerDashboard(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @GetMapping(value = "banking-operations", produces = "application/json", consumes = "application/json")
    public List<BankingOperation> getBankingOperations(@RequestBody OperationFilter filter) {
        User user = authenticationService.getPrincipal().getUser();
        return customerService.getBankingOperationsByUser(user, filter);
    }

    @GetMapping(value = "service-operations", produces = "application/json")
    public List<BankServiceOperation> getServiceOperations(@RequestBody OperationFilter filter) {
        User user = authenticationService.getPrincipal().getUser();
        return customerService.getServiceOperationsByUser(user, filter);
    }

    @GetMapping(value = "bank-accounts", produces = "application/json")
    public List<BankAccount> getBankAccounts() {
        User user = authenticationService.getPrincipal().getUser();
        return customerService.getBankAccountsByUser(user);
    }

    @PostMapping(value = "bank-account/{id}/deposit", produces = "application/json", consumes = "application/json")
    public BankingOperation deposit(@PathVariable Integer id, @RequestBody OperationForm form) {
        User user = authenticationService.getPrincipal().getUser();
        Float amount = form.getAmount();
        return customerService.makeDeposit(user, id, amount);
    }

    @PostMapping(value = "bank-account/{id}/withdraw", produces = "application/json" , consumes = "application/json")
    public BankingOperation withdraw(@PathVariable Integer id, @RequestBody OperationForm form) {
        User user = authenticationService.getPrincipal().getUser();
        Float amount = form.getAmount();
        return customerService.makeWithdraw(user, id, amount);
    }

    @PostMapping(value = "bank-account/{id}/transfer", produces = "application/json" , consumes = "application/json")
    public BankingOperation transfer(@PathVariable Integer id, @RequestBody OperationForm form) {
        User user = authenticationService.getPrincipal().getUser();
        Float amount = form.getAmount();
        String recipientIban = form.getRecipientIban();
        return customerService.makeTransfer(user, id, amount, recipientIban);
    }

    @PostMapping(value = "bank-account/{id}/car-tax", produces = "application/json" , consumes = "application/json")
    public BankServiceOperation carTax(@PathVariable Integer id, @RequestBody CarTaxForm form) {
        User user = authenticationService.getPrincipal().getUser();
        Float amount = form.getAmount();
        String licensePlate = form.getLicensePlate();
        return customerService.carTax(user, id, amount, licensePlate);
    }

    @PostMapping(value = "bank-account/{id}/refill", produces = "application/json" , consumes = "application/json")
    public BankServiceOperation refill(@PathVariable Integer id, @RequestBody RefillForm form) {
        User user = authenticationService.getPrincipal().getUser();
        Float amount = form.getAmount();
        String phoneNumber = form.getPhoneNumber();
        return customerService.refill(user, id, amount, phoneNumber);
    }
}
