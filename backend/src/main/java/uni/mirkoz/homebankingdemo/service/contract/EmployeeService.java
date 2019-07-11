package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import java.util.List;
import java.util.stream.Stream;

@Service@Transactional
public interface EmployeeService {

    List<BankingOperation> getBankingOperations(Employee employee, OperationFilter operationFilter);

    BankingOperation authorizeBankingOperation(Employee employee, Integer id);

    BankingOperation negateBankingOperation(Employee employee, Integer id);
}

