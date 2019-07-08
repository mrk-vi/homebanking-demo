package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.Employee;

public interface BankManagerService {
    BankBranch createBankBranch(BankBranch bankBranch);
    Employee assignEmployee(BankBranch bankBranch, Employee employee);
    BankProduct createBankProduct(BankProduct bankProduct);
    BankService createBankService(BankService bankService);

}
