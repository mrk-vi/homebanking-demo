package uni.mirkoz.homebankingdemo.controller.rest.bankmanager.form;

import lombok.Data;
import uni.mirkoz.homebankingdemo.model.banks.BankProduct;

@Data
public class CreateBankProductForm {
    private String name;
    private String description;
    private Float interestRate;
    private BankProduct.Type type;
}
