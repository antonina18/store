package store.receipt;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.stereotype.Component;
import store.bargain.Bargain;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

@Component
public class Receipt {

    private final Bargain bargain;

    public Receipt(Bargain bargain) {
        this.bargain = bargain;
    }

    @JsonUnwrapped
    private Map<Item, Integer> itemUnitMap = new HashMap<>();
    private int total = 0;
    private int toPay = 0;
    private int bargainAmount =0;

    public void addItems(Integer unit, Item item) {
        itemUnitMap.merge(item, unit, Integer::sum);
        calculate();
    }


    private void calculate() {
        total = itemUnitMap.entrySet().stream()
                .mapToInt(e -> {
                    int itemPrice = 0;
                    if (e.getKey().getSpecialPrice() == null) {
                        itemPrice += e.getValue() * e.getKey().getPrice();
                    } else {
                        Integer unitsForSpecialPrice = e.getKey().getSpecialPrice().getUnit();
                        Integer specialPriceAmount = e.getKey().getSpecialPrice().getPrice();
                        Integer normalPrice = e.getKey().getPrice();
                        Integer unitsOfItem = e.getValue();
                        if (unitsOfItem < unitsForSpecialPrice) {
                            itemPrice += e.getValue() * e.getKey().getPrice();
                        } else {
                            int aggregatedUnitsSpecialPriced = unitsOfItem/unitsForSpecialPrice;
                            int unitsNormalPriced = unitsOfItem - (aggregatedUnitsSpecialPriced * unitsForSpecialPrice);
                            itemPrice += aggregatedUnitsSpecialPriced * specialPriceAmount;
                            itemPrice += normalPrice * unitsNormalPriced;
                        }
                    }
                    return itemPrice;
                }).sum();
        bargainAmount = bargain.getDiscount(itemUnitMap);
        toPay = total - bargainAmount;
    }

    public void reset() {
        itemUnitMap = new HashMap<>();
        total = 0;
        toPay = 0;
        bargainAmount = 0;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (total != receipt.total) return false;
        return itemUnitMap != null ? itemUnitMap.equals(receipt.itemUnitMap) : receipt.itemUnitMap == null;

    }

    @Override
    public int hashCode() {
        int result = itemUnitMap != null ? itemUnitMap.hashCode() : 0;
        result = 31 * result + total;
        return result;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "bargain=" + bargain +
                ", itemUnitMap=" + itemUnitMap +
                ", total=" + total +
                ", toPay=" + toPay +
                ", bargainAmount=" + bargainAmount +
                '}';
    }

    public Map<Item, Integer> getItemUnitMap() {
        return itemUnitMap;
    }

    public Bargain getBargain() {
        return bargain;
    }

    public int getToPay() {
        return toPay;
    }

    public int getBargainAmount() {
        return bargainAmount;
    }
}
