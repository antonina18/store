package store.item;

import org.springframework.data.jpa.repository.JpaRepository;
import store.bargain.PromotionItems;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    Optional<Item> findByName(String name);
}
