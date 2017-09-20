package store.receipt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.bargain.BargainService;
import store.basket.Basket;
import store.item.Item;

import java.util.Map;

@Component
@Data
@NoArgsConstructor
public class Receipt {

    private BargainService bargainService;
    private Basket basket;

    @Autowired
    public Receipt(BargainService bargainService, Basket basket) {
        this.bargainService = bargainService;
        this.basket = basket;
    }


    private int total = 0;
    private int toPay = 0;
    private int bargainAmount =0;


    public Integer getToPay(){
        calculate();
        return toPay;
    }


    private void calculate() {
        Map<Item, Integer> itemUnitMap = basket.getItemUnitMap();
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

    public Map<Item, Integer> getReceipt(){
        return basket.getItemUnitMap();
    }

}
