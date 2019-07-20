package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.accounts.OperationFilter;
import uni.mirkoz.homebankingdemo.model.accounts.*;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.List;

@Service@Transactional
public interface CustomerService {

    List<BankingOperation> getBankingOperationsByUser(User user, OperationFilter filter);

    List<BankServiceOperation> getServiceOperationsByUser(User user, OperationFilter filter);

    List<BankAccount> getBankAccountsByUser(User user);

    BankingOperation makeDeposit(User user, Integer bankAccountId, Float amount);

    BankingOperation makeWithdraw(User user, Integer bankAccountId, Float amount);

    BankingOperation makeTransfer(User user, Integer bankAccountId, Float amount, String recipientIban);

    BankServiceOperation refill(User user, Integer bankAccountId, Float amount, String phoneNumber);

    BankServiceOperation carTax(User user, Integer bankAccountId, Float amount, String licensePlate);
}
