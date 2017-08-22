package store.item;

import org.springframework.stereotype.Service;
import store.basket.Basket;
import store.receipt.Receipt;

import java.util.Optional;

@Service
public class BuyService {

    private final Receipt receipt;
    private final ItemRepository itemRepository;

    public BuyService(Receipt receipt, ItemRepository itemRepository) {
        this.receipt = receipt;
        this.itemRepository = itemRepository;
    }

    public Integer buy(BuyItemDTO buyItemDTO) {
        Optional<Item> oItem = itemRepository.findByName(buyItemDTO.getName());
        oItem.ifPresent(Basket::addToBasket);
        return receipt.getToPay();
    }
}
