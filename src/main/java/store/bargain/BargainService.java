package store.bargain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import store.item.Item;
import store.utils.MapperHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class BargainService {

    private final BargainRepository bargainRepository;
    private final MapperHandler mapperHandler;

    public void addPromotion(PromotionItemDTO promotionItemDTO) {
        bargainRepository
                .save(mapperHandler
                        .transformTo(promotionItemDTO, PromotionItems.class));
    }

    public List<PromotionItemDTO> getPromotionItems() {
        List<PromotionItems> promotionItems = bargainRepository.findAll();
        return promotionItems.stream()
                .map(promotionItem -> mapperHandler.transformTo(promotionItem, PromotionItemDTO.class))
                .collect(Collectors.toList());
    }

    public Integer getDiscount(Map<Item, Integer> itemUnitMap){
        final List<String> itemNameList = getItemsNames(itemUnitMap);
        final List<PromotionItems> promotionItems = bargainRepository.findAll();

        return promotionItems.stream()
                .filter(e-> itemNameList.contains(e.getItemX()) && itemNameList.contains(e.getItemY()))
                .mapToInt(PromotionItems::getDiscount)
                .sum();
    }

    private List<String> getItemsNames(Map<Item, Integer> itemUnitMap) {
        return itemUnitMap.entrySet().stream()
                .map(e -> e.getKey().getName())
                .collect(toList());
    }
}
