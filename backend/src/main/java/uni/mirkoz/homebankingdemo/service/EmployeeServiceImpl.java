package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.service.contract.EmployeeService;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<BankingOperation> getBankingOperations(Employee employee, OperationFilter operationFilter) {
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation authorizeBankingOperation(Employee employee, Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation negateBankingOperation(Employee employee, Integer id) {
        throw new NotImplementedException();
    }
}
