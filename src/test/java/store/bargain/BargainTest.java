package store.bargain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import store.item.Item;
import store.item.SpecialPrice;
import store.stub.ItemObjectMother;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static store.stub.PromotionItemsObjectMother.promotionItems;

@RunWith(MockitoJUnitRunner.class)
public class BargainTest {


    @InjectMocks
    private BargainService bargainService;

    @Mock
    private BargainRepository bargainRepository;

    @Test
    public void shouldReturnDiscount() throws Exception {
        //given
        int expected = 3;
        Map<Item, Integer> itemUnitsMap = createItemUnitMap();
        given(bargainRepository.findAll()).willReturn(createPromotionItems());

        //when
        final Integer actual = bargainService.getDiscount(itemUnitsMap);

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldnotReturnDiscount() throws Exception {
        //given
        int expected = 0;
        Map<Item, Integer> itemUnitsMap = createWrongItemUnitMap();
        given(bargainRepository.findAll()).willReturn(createPromotionItems());

        //when
        final Integer actual = bargainService.getDiscount(itemUnitsMap);

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnDiscountOnlyForTwoProducts() throws Exception {
        //given
        int expected = 0;
        Map<Item, Integer> itemUnitsMap = createWrongItemUnitMap();
        SpecialPrice specialPriceForBread = ItemObjectMother.specialPrice(3, 10);
        Item bread = ItemObjectMother.itemWithSpecialPrice("bread", 12, specialPriceForBread);
        itemUnitsMap.put(bread,2);
        given(bargainRepository.findAll()).willReturn(createPromotionItems());

        //when
        final Integer actual = bargainService.getDiscount(itemUnitsMap);

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    private Map<Item, Integer> createItemUnitMap() {
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForBanana = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForCherry = ItemObjectMother.specialPrice(3, 10);
        Item banana = ItemObjectMother.itemWithSpecialPrice("banana", 12, specialPriceForBanana);
        Item cherry = ItemObjectMother.itemWithSpecialPrice("cherry", 3, specialPriceForCherry);

        itemUnitsMap.put(banana, 7);
        itemUnitsMap.put(cherry, 3);
        return itemUnitsMap;
    }

    private Map<Item, Integer> createWrongItemUnitMap() {
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForButter = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForBread = ItemObjectMother.specialPrice(3, 10);
        Item butter = ItemObjectMother.itemWithSpecialPrice("butter", 12, specialPriceForButter);
        Item bread = ItemObjectMother.itemWithSpecialPrice("bread", 3, specialPriceForBread);

        itemUnitsMap.put(butter, 7);
        itemUnitsMap.put(bread, 3);
        itemUnitsMap.put(butter, 2);
        return itemUnitsMap;
    }


    private List<PromotionItems> createPromotionItems(){
        PromotionItems promotionItem = promotionItems("banana", "cherry", 3);
        PromotionItems promotionItem1 = promotionItems("bread", "cherry", 2);
        PromotionItems promotionItem2 = promotionItems("banana", "bread", 1);
        List<PromotionItems> promotionsItems = new ArrayList<>();
        promotionsItems.add(promotionItem);
        promotionsItems.add(promotionItem1);
        promotionsItems.add(promotionItem2);
        return promotionsItems;

    }

}