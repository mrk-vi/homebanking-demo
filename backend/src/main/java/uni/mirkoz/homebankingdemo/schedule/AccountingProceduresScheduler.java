package uni.mirkoz.homebankingdemo.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uni.mirkoz.homebankingdemo.service.contract.BankSystemService;

@Component
public class AccountingProceduresScheduler {
    private BankSystemService bankSystemService;

    public AccountingProceduresScheduler(BankSystemService bankSystemService) {
        this.bankSystemService = bankSystemService;
    }

//    @Scheduled(fixedDelay = 10000)
//    @Scheduled(cron = "0 15 10 15 * ?")
    @Scheduled(cron = "${cron.interests-expression}")
    public void scheduledApplyInterests(){
        this.bankSystemService.applyInterests();
    }
}
