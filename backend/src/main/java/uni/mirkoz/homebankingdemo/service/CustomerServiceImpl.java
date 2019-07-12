package uni.mirkoz.homebankingdemo.service;

import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uni.mirkoz.homebankingdemo.model.accounts.*;
import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.BankService;
import uni.mirkoz.homebankingdemo.model.users.User;
import uni.mirkoz.homebankingdemo.repository.accounts.BankAccountRepository;
import uni.mirkoz.homebankingdemo.repository.accounts.BankingOperationRepository;
import uni.mirkoz.homebankingdemo.repository.accounts.BankServiceOperationRepository;
import uni.mirkoz.homebankingdemo.repository.accounts.BankingOperationSpecs;
import uni.mirkoz.homebankingdemo.repository.banks.BankProductRepository;
import uni.mirkoz.homebankingdemo.repository.banks.BankServiceRepository;
import uni.mirkoz.homebankingdemo.service.contract.CustomerService;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CustomerServiceImpl implements CustomerService {

    private BankAccountRepository bankAccountRepository;
    private BankingOperationRepository bankingOperationRepository;
    private BankServiceOperationRepository bankServiceOperationRepository;
    private BankServiceRepository bankServiceRepository;

    public CustomerServiceImpl(BankAccountRepository bankAccountRepository, BankingOperationRepository bankingOperationRepository, BankServiceOperationRepository bankServiceOperationRepository, BankServiceRepository bankServiceRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.bankingOperationRepository = bankingOperationRepository;
        this.bankServiceOperationRepository = bankServiceOperationRepository;
        this.bankServiceRepository = bankServiceRepository;
    }

    @Override
    public List<BankingOperation> getBankingOperationsByUser(User user, OperationFilter filter) {
        return bankingOperationRepository.findAll(BankingOperationSpecs.filterOperationsByUser(user, filter));
    }

    @Override
    public List<BankServiceOperation> getServiceOperationsByUser(User user, OperationFilter filter) {
        return bankServiceOperationRepository.findByBankAccount_Customer_User(user);
    }

    @Override
    public List<BankAccount> getBankAccountsByUser(User user) {
        return bankAccountRepository.findBankAccountsByCustomer_User(user);
    }

    @Override
    public BankingOperation makeDeposit(User user, Integer bankAccountId, Float amount) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountsByIdAndCustomer_User(bankAccountId, user);

        BankingOperation operation = new BankingOperation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setOperationState(OperationState.OPEN);

        operation.setOperationType(OperationType.DEPOSIT);

        return bankingOperationRepository.save(operation);
    }

    @Override
    public BankingOperation makeWithdraw(User user, Integer bankAccountId, Float amount) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountsByIdAndCustomer_User(bankAccountId, user);

        BankingOperation operation = new BankingOperation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setOperationState(OperationState.OPEN);

        operation.setOperationType(OperationType.WITHDRAW);

        return bankingOperationRepository.save(operation);
    }

    @Override
    public BankingOperation makeTransfer(User user, Integer bankAccountId, Float amount, String recipientIban) {
        BankAccount sender = bankAccountRepository.findBankAccountsByIdAndCustomer_User(bankAccountId, user);

        BankingOperation operation = new BankingOperation();
        operation.setBankAccount(sender);
        operation.setAmount(amount);
        operation.setRecipientIban(recipientIban);
        operation.setOperationState(OperationState.OPEN);

        operation.setOperationType(OperationType.TRANSFER);

        return bankingOperationRepository.save(operation);
    }

    @Override
    public BankServiceOperation refill(User user, Integer bankAccountId, Float amount, String phoneNumber) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountsByIdAndCustomer_User(bankAccountId, user);
        Bank bank = bankAccount.getCustomer().getBankBranch().getBank();
        BankService bankService;
        try{
           bankService = bankServiceRepository.findByBankAndType(bank, BankService.Type.REFILL);
        } catch (NoSuchElementException nse){
           throw new NoSuchElementException();
        }

        BankServiceOperation operation  = new BankServiceOperation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setOperationType(OperationType.REFILL);
        operation.setOperationState(OperationState.AUTHORIZED);
        operation.setBankService(bankService);

        bankAccount.setBalance(bankAccount.getBalance()-operation.getAmount());
        bankAccountRepository.save(bankAccount);
        return bankServiceOperationRepository.save(operation);
    }

    @Override
    public BankServiceOperation carTax(User user, Integer bankAccountId, Float amount, String licensePlate) {
        BankAccount bankAccount = bankAccountRepository.findBankAccountsByIdAndCustomer_User(bankAccountId, user);
        Bank bank = bankAccount.getCustomer().getBankBranch().getBank();
        BankService bankService;
        try{
            bankService = bankServiceRepository.findByBankAndType(bank, BankService.Type.CAR_TAX);
        } catch (NoSuchElementException nse){
            throw new NoSuchElementException();
        }

        BankServiceOperation operation  = new BankServiceOperation();
        operation.setBankAccount(bankAccount);
        operation.setAmount(amount);
        operation.setOperationType(OperationType.CAR_TAX);
        operation.setOperationState(OperationState.AUTHORIZED);
        operation.setBankService(bankService);

        bankAccount.setBalance(bankAccount.getBalance()-operation.getAmount());
        bankAccountRepository.save(bankAccount);
        return bankServiceOperationRepository.save(operation);
    }
}
