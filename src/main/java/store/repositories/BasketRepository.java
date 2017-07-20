package store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import store.models.Basket;

public interface BasketRepository extends JpaRepository<Integer, Basket> {

}
