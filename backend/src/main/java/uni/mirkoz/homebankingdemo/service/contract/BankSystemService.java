package uni.mirkoz.homebankingdemo.service.contract;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service@Transactional
public interface BankSystemService {
    void applyInterests();
}
