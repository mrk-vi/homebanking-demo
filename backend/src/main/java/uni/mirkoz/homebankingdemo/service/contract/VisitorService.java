package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.mirkoz.homebankingdemo.model.banks.*;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import java.util.List;

@Service@Transactional
public interface VisitorService {

    List<BankService> getServices();

    List<BankProduct> getProducts();

    List<Bank> getBanks();

    Customer sendRegistrationRequest(Customer registrationRequest, Integer bankBranchId);
}
