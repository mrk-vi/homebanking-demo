package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.banks.*;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import java.util.List;

public interface VisitorService {
    List<BankService> listServices();
    List<BankProduct> listProducts();
    List<Bank> listBanks();
    void sendRegistrationRequest(Customer registrationRequest);

}
