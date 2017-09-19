package store.item;

import org.springframework.stereotype.Service;
import store.receipt.Receipt;
import store.utils.MapperHandler;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final MapperHandler mapperHandler;

    public ItemService(ItemRepository itemRepository, MapperHandler mapperHandler) {
        this.itemRepository = itemRepository;
        this.mapperHandler = mapperHandler;
    }

    public void addItem(ItemDTO itemDTO) {
        Item item = mapperHandler.transformTo(itemDTO, Item.class);
        itemRepository.save(item);
    }

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream()
                .map(item-> mapperHandler.transformTo(items, ItemDTO.class))
                .collect(Collectors.toList());

    }
}
