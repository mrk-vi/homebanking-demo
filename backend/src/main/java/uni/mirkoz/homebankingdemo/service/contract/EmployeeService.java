package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.controller.ws.customer.form.OperationFilterForm;

import java.util.stream.Stream;

@Service@Transactional
public interface EmployeeService {

    BankingOperation authorizeBankingOperation(BankingOperation bankingOperation);

    BankingOperation negateBankingOperation(BankingOperation bankingOperation);

    Stream<BankingOperation> exportBankingOperations(OperationFilterForm operationFilterForm);
}
