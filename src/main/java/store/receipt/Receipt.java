package store.receipt;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.bargain.Bargain;
import store.bargain.BargainService;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@NoArgsConstructor
public class Receipt {

    private Bargain bargain;
    private BargainService bargainService;

    @Autowired
    public Receipt(Bargain bargain, BargainService bargainService) {
        this.bargain = bargain;
        this.bargainService = bargainService;
    }

    @JsonUnwrapped
    private Map<Item, Integer> itemUnitMap = new HashMap<>();
    private int total = 0;
    private int toPay = 0;
    private int bargainAmount =0;

    public void addItems(Integer unit, Item item) {
//        itemUnitMap.merge(item, unit, Integer::sum);
        calculate();
    }

    public Integer getToPay(){
        calculate();
        return toPay;
    }


    public void calculate() {
        total = itemUnitMap.entrySet().stream()
                .mapToInt(item -> {
                    int itemPrice = 0;
                    if (item.getKey().getSpecialPrice() == null) {
                        itemPrice = simpleCalculate(item, itemPrice);
                    } else {
                        itemPrice = calculateWithSpecialPrice(item, itemPrice);
                    }
                    return itemPrice;
                }).sum();
        bargainAmount = bargainService.getDiscount(itemUnitMap);
        toPay = total - bargainAmount;
    }

    private int calculateWithSpecialPrice(Map.Entry<Item, Integer> item, int itemPrice) {
        Integer unitsForSpecialPrice = item.getKey().getSpecialPrice().getUnit();
        Integer unitsOfItem = item.getValue();
        if (unitsOfItem < unitsForSpecialPrice) {
            itemPrice = simpleCalculate(item, itemPrice);
        } else {
            itemPrice = calculateWithBargains(item, itemPrice, unitsForSpecialPrice, unitsOfItem);
        }
        return itemPrice;
    }

    private int calculateWithBargains(Map.Entry<Item, Integer> item, int itemPrice, Integer unitsForSpecialPrice, Integer unitsOfItem) {
        Integer specialPriceAmount = item.getKey().getSpecialPrice().getPrice();
        Integer normalPrice = item.getKey().getPrice();

        int aggregatedUnitsSpecialPriced = unitsOfItem/unitsForSpecialPrice;
        int unitsNormalPriced = unitsOfItem - (aggregatedUnitsSpecialPriced * unitsForSpecialPrice);
        itemPrice += aggregatedUnitsSpecialPriced * specialPriceAmount;
        itemPrice += normalPrice * unitsNormalPriced;
        return itemPrice;
    }

    private int simpleCalculate(Map.Entry<Item, Integer> item, int itemPrice) {
        itemPrice += item.getValue() * item.getKey().getPrice();
        return itemPrice;
    }

    public void reset() {
        itemUnitMap = new HashMap<>();
        total = 0;
        toPay = 0;
        bargainAmount = 0;
    }

}
