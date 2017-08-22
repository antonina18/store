package store.basket;

import org.springframework.stereotype.Component;
import store.item.Item;

import java.util.ArrayList;
import java.util.List;

@Component
public class Basket {

    private List<Item> items;

    public Basket() {
        items = new ArrayList<>();
    }

    public void addToBasket(Item item){
        items.add(item);
    }
}
