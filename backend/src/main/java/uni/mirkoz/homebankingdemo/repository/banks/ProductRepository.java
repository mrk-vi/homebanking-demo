package uni.mirkoz.homebankingdemo.repository.banks;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.mirkoz.homebankingdemo.model.banks.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
