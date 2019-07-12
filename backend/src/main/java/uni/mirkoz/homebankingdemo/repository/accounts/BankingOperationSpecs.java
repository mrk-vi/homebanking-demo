package uni.mirkoz.homebankingdemo.repository.accounts;

import org.springframework.data.jpa.domain.Specification;
import uni.mirkoz.homebankingdemo.model.accounts.*;
import uni.mirkoz.homebankingdemo.model.banks.BankBranch;
import uni.mirkoz.homebankingdemo.model.users.Customer;
import uni.mirkoz.homebankingdemo.model.users.User;

import java.util.Date;

public class BankingOperationSpecs {

    public static Specification<BankingOperation>  isUser(User user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                                                    .equal(root
                                                            .<BankAccount>get("bankAccount")
                                                            .<Customer>get("customer")
                                                            .<User>get("user"), user);
    }

    public static Specification<BankingOperation>  isBankBranch(BankBranch bankBranch) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                                                    .equal(root
                                                            .<BankAccount>get("bankAccount")
                                                            .<Customer>get("customer")
                                                            .<BankBranch>get("bankBranch"), bankBranch);
    }


    public static Specification<BankingOperation>  dateGTE(Date date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("created"), date);
    }
    public static Specification<BankingOperation>  dateLTE(Date date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.<Date>get("created"), date);
    }
    public static Specification<BankingOperation> amountGTE(Float amount) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.<Float>get("amount"), amount);
    }
    public static Specification<BankingOperation> amountLTE(Float amount) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.<Float>get("amount"), amount);
    }
    public static Specification<BankingOperation> type(OperationType type) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<OperationType>get("operationType"), type);
    }
    public static Specification<BankingOperation> state(OperationState state) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<OperationType>get("operationState"), state);
    }

    public static Specification<BankingOperation> filterOperationsByUser(User user, OperationFilter filter) {
        Specification<BankingOperation> spec = Specification.where(isUser(user));
        return spec.and(parseFilter(filter));
    }

    public static Specification<BankingOperation> filterOperationsByBankBranch(BankBranch bankBranch, OperationFilter filter) {
        Specification<BankingOperation> spec = Specification.where(isBankBranch(bankBranch));
        return spec.and(parseFilter(filter));
    }

    public static Specification<BankingOperation> parseFilter(OperationFilter filter) {
        Specification<BankingOperation> spec = Specification.where(null);
        if (filter.getFromDay() != null){
            spec = spec.and(dateGTE(filter.getFromDay()));
        }
        if (filter.getToDay() != null){
            spec = spec.and(dateLTE(filter.getToDay()));
        }
        if (filter.getFromAmount() != null){
            spec = spec.and(amountGTE(filter.getFromAmount()));
        }
        if (filter.getToAmount() != null){
            spec = spec.and(amountLTE(filter.getToAmount()));
        }
        if (filter.getType() != null){
            spec = spec.and(type(filter.getType()));
        }
        if (filter.getState() != null){
            spec = spec.and(state(filter.getState()));
        }
        return spec;
    }
}
