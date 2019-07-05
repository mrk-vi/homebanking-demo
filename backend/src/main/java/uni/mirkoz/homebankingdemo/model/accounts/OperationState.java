package uni.mirkoz.homebankingdemo.model.accounts;

import javax.persistence.Entity;

public enum OperationState {
    OPEN,
    REJECTED,
    AUTHORIZED
}
