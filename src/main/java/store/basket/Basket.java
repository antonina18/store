package store.basket;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.stereotype.Component;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

@Component
public class Basket {

    @JsonUnwrapped
    private Map<Item, Integer> itemUnitMap = new HashMap<>();

    public Basket() {
        itemUnitMap = new HashMap<>();
    }

    public void addToBasket(Item item, Integer units){
        itemUnitMap.put(item, units);
    }

    public Map<Item, Integer> getItemUnitMap() {
        return itemUnitMap;
    }

    public void setItemUnitMap(Map<Item, Integer> itemUnitMap) {
        this.itemUnitMap = itemUnitMap;
    }
}
