package uni.mirkoz.homebankingdemo.model.accounts;

import lombok.Data;

import java.util.Date;

@Data
public class OperationFilter {
    private Date fromDay;
    private Date toDay;
    private Float fromAmount;
    private Float toAmount;
    private OperationState state;
    private OperationType type;
}
