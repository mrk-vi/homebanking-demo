package uni.mirkoz.homebankingdemo.service;

import uni.mirkoz.homebankingdemo.model.banks.Bank;
import uni.mirkoz.homebankingdemo.model.banks.Product;
import uni.mirkoz.homebankingdemo.model.banks.Service;
import uni.mirkoz.homebankingdemo.model.users.Customer;

import java.util.List;

public interface VisitorService {
    List<Service> listServices();
    List<Product> listProducts();
    List<Bank> listBanks();
    void sendRegistrationRequest(Customer registrationRequest);

}
