package uni.mirkoz.homebankingdemo.controller.ws.customer.form;

import lombok.Data;

@Data
public class OperationForm {
    private Float amount;
    private String recipientIban;
}
