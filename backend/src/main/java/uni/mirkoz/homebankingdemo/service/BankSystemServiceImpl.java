package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.model.accounts.BankAccount;
import uni.mirkoz.homebankingdemo.model.accounts.BankingOperation;
import uni.mirkoz.homebankingdemo.model.accounts.OperationState;
import uni.mirkoz.homebankingdemo.model.accounts.OperationType;
import uni.mirkoz.homebankingdemo.repository.accounts.BankAccountRepository;
import uni.mirkoz.homebankingdemo.repository.accounts.BankingOperationRepository;
import uni.mirkoz.homebankingdemo.service.contract.BankSystemService;

import java.util.List;

@Component
public class BankSystemServiceImpl implements BankSystemService {

    private BankAccountRepository bankAccountRepository;
    private BankingOperationRepository bankingOperationRepository;

    public BankSystemServiceImpl(BankAccountRepository bankAccountRepository, BankingOperationRepository bankingOperationRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankingOperationRepository = bankingOperationRepository;
    }

    @Override
    public void applyInterests() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        bankAccounts.forEach( bankAccount -> {
            float interestRate = bankAccount.getBankProduct().getInterestRate();
            float balance = bankAccount.getBalance();
            float interestAmount = calculateInterestAmount(interestRate, balance);
            bankAccount.setBalance(balance-interestAmount);
            bankAccountRepository.save(bankAccount);

            BankingOperation operation = new BankingOperation();
            operation.setOperationType(OperationType.INTERESTS);
            operation.setOperationState(OperationState.AUTHORIZED);
            operation.setAmount(interestAmount);
            operation.setBankAccount(bankAccount);
            bankingOperationRepository.save(operation);
        });
    }

    private static float calculateInterestAmount (float interestRate, float balance) {
        return (interestRate*balance)/100;
    }
}
