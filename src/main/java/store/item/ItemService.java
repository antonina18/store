package store.item;

import org.springframework.stereotype.Service;
import store.receipt.Receipt;
import store.utils.MapperHandler;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final Receipt receipt;
    private final ItemRepository itemRepository;
    private final MapperHandler mapperHandler;

    public ItemService(Receipt receipt, ItemRepository itemRepository, MapperHandler mapperHandler) {
        this.receipt = receipt;
        this.itemRepository = itemRepository;
        this.mapperHandler = mapperHandler;
    }

    public Integer addItems(Integer unit, Item item) {
        itemRepository.save(item);
//        receipt.addItems(unit, item);
        return receipt.getToPay();
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item-> mapperHandler.transformTo(items, ItemDTO.class))
                .collect(Collectors.toList());

    }
}
