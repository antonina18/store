package store.receipt;

import org.springframework.stereotype.Component;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

@Component
public class Receipt {

    private Map<Item, Integer> itemUnitMap = new HashMap<>();
    private int totalPrice = 0;

    public void addItems(Integer unit, Item item) {
        itemUnitMap.merge(item, unit, Integer::sum);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = itemUnitMap.entrySet().stream()
                .mapToInt(e -> {
                    int itemPrice = 0;
                    if (e.getKey().getSpecialPrice() == null) {
                        itemPrice += e.getValue() * e.getKey().getPrice();
                    } else {
                        Integer unitsForSpecialPrice = e.getKey().getSpecialPrice().getUnit();
                        Integer specialPriceAmmount = e.getKey().getSpecialPrice().getPrice();
                        Integer normalPrice = e.getKey().getPrice();
                        Integer unitsOfItem = e.getValue();
                        if (unitsOfItem < unitsForSpecialPrice) {
                            itemPrice += e.getValue() * e.getKey().getPrice();
                        } else {
                            int aggregatedUnitsSpecialPriced = unitsOfItem/unitsForSpecialPrice;
                            int unitsNormalPriced = unitsOfItem - (aggregatedUnitsSpecialPriced * unitsForSpecialPrice);
                            itemPrice += aggregatedUnitsSpecialPriced * specialPriceAmmount;
                            itemPrice += normalPrice * unitsNormalPriced;
                        }
                    }
                    return itemPrice;
                }).sum();
    }

    public void reset() {
        itemUnitMap = new HashMap<>();
        totalPrice = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

}
