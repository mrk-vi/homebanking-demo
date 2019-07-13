package uni.mirkoz.homebankingdemo.controller.ws.bytestream;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.service.contract.AuthenticationService;
import uni.mirkoz.homebankingdemo.service.contract.EmployeeService;
import uni.mirkoz.homebankingdemo.util.PDFGenerator;

import java.util.List;

@Controller
@RequestMapping
@ResponseBody
public class PdfController {

    private EmployeeService employeeService;
    private AuthenticationService authenticationService;

    public PdfController(EmployeeService employeeService, AuthenticationService authenticationService) {
        this.employeeService = employeeService;
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/employee/banking-operations/pdf",
            produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE, MediaType.APPLICATION_PDF_VALUE},
            consumes = "application/json")
    public ResponseEntity<Resource> exportBankingOperationsPDF(@RequestBody OperationFilter filter) {

        Employee employee = authenticationService.getPrincipal().getEmployee().get();
        List<BankingOperation> bankingOperations = employeeService.getBankingOperations(employee, filter);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=operations.pdf")
                .body(new ByteArrayResource(PDFGenerator.customerPDFReport(bankingOperations)));
    }
}
