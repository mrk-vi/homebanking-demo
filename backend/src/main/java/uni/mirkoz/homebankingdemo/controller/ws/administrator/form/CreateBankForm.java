package uni.mirkoz.homebankingdemo.controller.ws.administrator.form;

import lombok.Data;

@Data
public class CreateBankForm {
    private String bankName;
    private String bankAddress;
    private String bankDescription;
    // TODO handling images uploads
    private String username;
    private String mail;
}
