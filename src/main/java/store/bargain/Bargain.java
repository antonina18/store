package store.bargain;

import org.springframework.stereotype.Component;
import store.item.Item;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class Bargain {

    private static final Map<PromotionItems,Integer> promotionMap = Stream.of(
            new AbstractMap.SimpleEntry<>(new PromotionItems("butter", "bread"), 3),
            new AbstractMap.SimpleEntry<>(new PromotionItems("milk", "juice"), 2)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public Map<PromotionItems, Integer> getPromotionMap() {
        return promotionMap;
    }

    public Integer getDiscount(Map<Item, Integer> itemUnitMap){
        final List<String> itemNameList = itemUnitMap.entrySet().stream()
                .map(e -> e.getKey().getName())
                .collect(toList());
        return promotionMap.entrySet().stream()
                .filter(e -> itemNameList.containsAll(Arrays.asList(e.getKey().getItemX(), e.getKey().getItemY())))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
