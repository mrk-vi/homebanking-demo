package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uni.mirkoz.homebankingdemo.model.accounts.*;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.users.Employee;
import uni.mirkoz.homebankingdemo.repository.accounts.BankAccountRepository;
import uni.mirkoz.homebankingdemo.repository.accounts.BankingOperationRepository;
import uni.mirkoz.homebankingdemo.service.contract.EmployeeService;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private BankingOperationRepository bankingOperationRepository;
    private BankAccountRepository bankAccountRepository;

    public EmployeeServiceImpl(BankingOperationRepository bankingOperationRepository, BankAccountRepository bankAccountRepository) {
        this.bankingOperationRepository = bankingOperationRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankingOperation> getBankingOperations(Employee employee, OperationFilter operationFilter) {

        BankBranch bankBranch = employee.getBankBranch();
        return bankingOperationRepository.findByBankAccount_Customer_BankBranch(bankBranch);
    }

    @Override
    public BankingOperation authorizeBankingOperation(Employee employee, Integer bankOperationId) {

        BankBranch bankBranch = employee.getBankBranch();
        BankingOperation operation = bankingOperationRepository.findByIdAndOperationStateAndBankAccount_Customer_BankBranch(
                bankOperationId,
                OperationState.OPEN,
                bankBranch
        );
        float amount = operation.getAmount();
        if (operation.getOperationType() != OperationType.TRANSFER) {
//        IF DEPOSIT OR WITHDRAW
//        -----------------------
            BankAccount bankAccount = bankAccountRepository.findByBankingOperationsContains(operation);
            float balance = 0;
            try {
                balance = bankAccount.getBalance();
            } catch (NullPointerException npe) {
            }
            bankAccount.setBalance(balance + amount);
        }
//
//        IF TRANSFER
//        -----------------------
//        BankAccount recipient = bankAccountRepository.findBankAccountsByIban(recipientIban);
//        float senderBalance = 0;
//        float recipientBalance = 0;
//        try {
//            senderBalance = sender.getBalance();
//            recipientBalance = recipient.getBalance();
//        } catch (NullPointerException npe) {}
//        sender.setBalance(senderBalance-amount);
//        recipient.setBalance(recipientBalance+amount);
        throw new NotImplementedException();
    }

    @Override
    public BankingOperation negateBankingOperation(Employee employee, Integer id) {
        throw new NotImplementedException();
    }
}
