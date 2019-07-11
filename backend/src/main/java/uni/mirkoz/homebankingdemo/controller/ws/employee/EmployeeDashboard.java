package uni.mirkoz.homebankingdemo.controller.ws.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;
import uni.mirkoz.homebankingdemo.service.contract.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employee")
@ResponseBody
public class EmployeeDashboard {

    private EmployeeService employeeService;
    private AuthenticationService authenticationService;

    public EmployeeDashboard(EmployeeService employeeService, AuthenticationService authenticationService) {
        this.employeeService = employeeService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(value = "banking-operations", produces = "application/json", consumes = "application/json")
    public List<BankingOperation> getBankingOperations(@RequestBody OperationFilter filter) {
        Employee employee = authenticationService.getPrincipal().getEmployee().get();
        return employeeService.getBankingOperations(employee, filter);
    }

    @PutMapping(value = "banking-operation/{id}/authorize", produces = "application/json", consumes = "application/json")
    public BankingOperation authorizeBankingOperation(@PathVariable Integer id) {
        Employee employee = authenticationService.getPrincipal().getEmployee().get();
        return employeeService.authorizeBankingOperation(employee, id);
    }

    @PutMapping(value = "banking-operation/{id}/negate", produces = "application/json", consumes = "application/json")
    public BankingOperation negateBankingOperation(@PathVariable Integer id) {
        Employee employee = authenticationService.getPrincipal().getEmployee().get();
        return employeeService.negateBankingOperation(employee, id);
    }

}
