package store.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import store.item.Item;

import java.util.Map;

@Service
@AllArgsConstructor
public class ReceiptService {

    private final Receipt receipt;

    public Map<Item, Integer> getReceipt() {
        return receipt.getReceipt();
    }
}
