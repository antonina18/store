package store.item;

import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Component
public class Bargain {

    private static final Map<Pair<String,String>,Integer> promotionMap = Stream.of(
            new AbstractMap.SimpleEntry<>(new Pair<>("butter", "bread"), 3),
            new AbstractMap.SimpleEntry<>(new Pair<>("milk", "juice"), 2)
    ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public Map<Pair<String, String>, Integer> getPromotionMap() {
        return promotionMap;
    }

    public Integer getDiscount(Map<Item, Integer> itemUnitMap){
        final List<String> itemNameList = itemUnitMap.entrySet().stream()
                .map(e -> e.getKey().getName())
                .collect(toList());
        return promotionMap.entrySet().stream()
                .filter(e -> itemNameList.containsAll(Arrays.asList(e.getKey().getKey(), e.getKey().getValue())))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
}
