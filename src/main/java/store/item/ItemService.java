package store.item;

import org.springframework.stereotype.Service;
import store.receipt.Receipt;

@Service
public class ItemService {

    private final Receipt receipt;

    public ItemService(Receipt receipt) {
        this.receipt = receipt;
    }

    public Integer addItems(Integer unit, Item item) {
        receipt.addItems(unit, item);
        return receipt.getToPay();
    }

}
