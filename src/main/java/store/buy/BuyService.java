package store.buy;

import org.springframework.stereotype.Service;
import store.basket.Basket;
import store.item.Item;
import store.item.ItemRepository;
import store.receipt.Receipt;

import java.util.Optional;

@Service
public class BuyService {

    private final Receipt receipt;
    private final Basket basket;
    private final ItemRepository itemRepository;

    public BuyService(Receipt receipt, Basket basket, ItemRepository itemRepository) {
        this.receipt = receipt;
        this.basket = basket;
        this.itemRepository = itemRepository;
    }

    public Integer buy(BuyItemDTO buyItemDTO) {
        Optional<Item> oItem = itemRepository.findByName(buyItemDTO.getName());
        oItem.ifPresent(item -> basket.add(item, buyItemDTO.getUnits()));
        return receipt.getToPay();
    }
}
