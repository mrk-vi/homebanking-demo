package uni.mirkoz.homebankingdemo.controller.ws.bankmanager.form;

import lombok.Data;
import uni.mirkoz.homebankingdemo.model.banks.BankService;

@Data
public class CreateBankServiceForm {
    private BankService.Type type;
}
