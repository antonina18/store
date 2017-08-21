package store.bargain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BargainRepository extends JpaRepository<PromotionItems,Integer> {
}
