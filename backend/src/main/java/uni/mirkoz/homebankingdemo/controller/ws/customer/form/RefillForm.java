package uni.mirkoz.homebankingdemo.controller.ws.customer.form;

import lombok.Data;

@Data
public class RefillForm {
    private String phoneNumber;
    private Float amount;
}
