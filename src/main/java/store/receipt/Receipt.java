package store.receipt;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.stereotype.Component;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

@Component
public class Receipt {

    @JsonUnwrapped
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (totalPrice != receipt.totalPrice) return false;
        return itemUnitMap != null ? itemUnitMap.equals(receipt.itemUnitMap) : receipt.itemUnitMap == null;

    }

    @Override
    public int hashCode() {
        int result = itemUnitMap != null ? itemUnitMap.hashCode() : 0;
        result = 31 * result + totalPrice;
        return result;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "itemUnitMap=" + itemUnitMap +
                ", totalPrice=" + totalPrice +
                '}';
    }

}
