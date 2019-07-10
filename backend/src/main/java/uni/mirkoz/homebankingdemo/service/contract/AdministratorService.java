package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import java.util.List;

@Service@Transactional
public interface AdministratorService {

    Bank saveBank(Bank bank, BankManager bankManager);

    Bank assignBankManager(Bank bank, BankManager bankManager);

    Bank ceaseBankManager(Bank bank);

    List<Bank> getBanks();

    List<BankManager> getBankManagers();

    List<Customer> getUnauthorizedCustomers();

    Bank getBank(Integer id);

    Customer authorizeCustomer(Integer customerId);
}
