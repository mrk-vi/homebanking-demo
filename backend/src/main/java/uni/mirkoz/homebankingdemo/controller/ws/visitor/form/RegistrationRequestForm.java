package uni.mirkoz.homebankingdemo.controller.ws.visitor.form;

import lombok.Data;

@Data
public class RegistrationRequestForm {
    private Integer bankBranchId;
    private String username;
    private String password;
    private String email;

}
