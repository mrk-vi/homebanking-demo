package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uni.mirkoz.homebankingdemo.controller.ws.customer.form.OperationFilterForm;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.accounts.BankServiceOperation;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.Operation;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.service.contract.CustomerService;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<Operation> getOperationsByUser(User user, OperationFilterForm filter) {
        throw new NotImplementedException();
    }

    @Override
    public List<BankAccount> getBankAccountsByUser(User user) {
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation makeDeposit(User user, Integer bankAccountId, Float amount) {
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation makeWithdraw(User user, Integer bankAccountId, Float amount) {
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation makeTransfer(User user, Integer bankAccountId, Float amount, String recipientIban) {
        throw new NotImplementedException();
    }

    @Override
    public BankServiceOperation refill(User user, Integer bankAccountId, Float amount, String phoneNumber) {
        throw new NotImplementedException();
    }

    @Override
    public BankServiceOperation carTax(User user, Integer bankAccountId, Float amount, String licensePlate) {
        throw new NotImplementedException();
    }
}
