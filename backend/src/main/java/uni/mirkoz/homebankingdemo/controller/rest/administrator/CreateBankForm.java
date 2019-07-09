package uni.mirkoz.homebankingdemo.controller.rest.administrator;

import lombok.Data;

@Data
class CreateBankForm {
    private String bankName;
    private String bankAddress;
    private String bankDescription;
    // TODO handling images uploads
    private String username;
    private String mail;
}
