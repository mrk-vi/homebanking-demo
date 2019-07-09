package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.BankManager;
import uni.mirkoz.homebankingdemo.model.users.Employee;

import java.util.List;

@Service@Transactional
public interface BankManagerService {

    BankBranch createBankBranch(Bank bank, BankBranch bankBranch);

    BankProduct createBankProduct(Bank bank, BankProduct bankProduct);

    BankService createBankService(Bank bank, BankService bankService);

    Employee assignEmployee(BankBranch bankBranch, Employee employee);

    BankProduct updateInterestRate(BankProduct bankProduct, Float interestRate);

    BankBranch getBankBranch(Integer id);

    BankProduct getBankProduct(Integer id);

    BankManager getBankManager(Integer id);

    Bank getBank(Integer id);

    List<BankBranch> getBankBranches(Integer id);

    List<BankProduct> getBankProducts(Integer id);

    List<BankService> getBankServices(Integer id);
}
