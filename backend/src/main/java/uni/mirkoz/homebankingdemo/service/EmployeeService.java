package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;

import java.util.stream.Stream;

public interface EmployeeService {
    BankingOperation authorizeBankingOperation(BankingOperation bankingOperation);
    BankingOperation negateBankingOperation(BankingOperation bankingOperation);
    Stream<BankingOperation> exportBankingOperations(OperationFilter operationFilter);
}
