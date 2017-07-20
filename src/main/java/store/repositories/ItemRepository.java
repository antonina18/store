package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.models.Item;

public interface ItemRepository extends JpaRepository<Integer, Item>{
}
