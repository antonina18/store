package store.stub;

import store.dtos.ItemDto;
import store.models.Item;

public class ItemObjectMother {

    public static ItemDto itemDto(String name, Integer price){
        ItemDto itemDto = new ItemDto();
        itemDto.setName(name);
        itemDto.setPrice(price);

        return itemDto;
    }

    public static Item item(String name, Integer price){
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);

        return item;
    }
}
