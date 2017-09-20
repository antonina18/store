package store.receipt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import store.bargain.Bargain;
import store.bargain.BargainService;
import store.basket.Basket;
import store.item.Item;
import store.item.SpecialPrice;
import store.stub.ItemObjectMother;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptTest {

    private Receipt receipt;

    @Mock
    private BargainService bargainService;

    @Mock
    private Basket basket;

    @Before
    public void setUp() {
        this.receipt = new Receipt(bargainService, basket);
    }

    @Test
    public void shouldReturnNormalPrice() throws Exception {
        //given
        int expected = 30;
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        Item banana = ItemObjectMother.item("banana", 12);
        Item cherry = ItemObjectMother.item("cherry", 3);
        itemUnitsMap.put(banana, 2);
        itemUnitsMap.put(cherry, 2);

        given(basket.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        int actual = receipt.getToPay();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnNormalPriceAlthoughGivenSpecialPrice() throws Exception {
        //given
        int expected = 15;
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForBanana = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForCherry = ItemObjectMother.specialPrice(3, 10);
        Item banana = ItemObjectMother.itemWithSpecialPrice("banana", 12, specialPriceForBanana);
        Item cherry = ItemObjectMother.itemWithSpecialPrice("cherry", 3, specialPriceForCherry);

        itemUnitsMap.put(banana, 1);
        itemUnitsMap.put(cherry, 1);

        given(basket.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        int actual = receipt.getToPay();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnReceiptIncludingBargain() throws Exception {
        //given
        int expected = 40;
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForBanana = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForCherry = ItemObjectMother.specialPrice(3, 10);
        Item banana = ItemObjectMother.itemWithSpecialPrice("banana", 12, specialPriceForBanana);
        Item cherry = ItemObjectMother.itemWithSpecialPrice("cherry", 3, specialPriceForCherry);

        itemUnitsMap.put(banana, 3);
        itemUnitsMap.put(cherry, 3);

        given(basket.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        int actual = receipt.getToPay();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnReceiptWithAggregateTwoSpecialPrices() throws Exception {
        //given
        int expected = 82;
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForBanana = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForCherry = ItemObjectMother.specialPrice(3, 10);
        Item banana = ItemObjectMother.itemWithSpecialPrice("banana", 12, specialPriceForBanana);
        Item cherry = ItemObjectMother.itemWithSpecialPrice("cherry", 3, specialPriceForCherry);

        itemUnitsMap.put(banana, 7);
        itemUnitsMap.put(cherry, 3);


        given(basket.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        int actual = receipt.getToPay();

        //then
        assertThat(actual, is(equalTo(expected)));
    }


    @Test
    public void shouldReturnReceiptWithAggregateItemsIncludingAllBargains() throws Exception {
        //given
        int expected = 79;
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        SpecialPrice specialPriceForBanana = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForCherry = ItemObjectMother.specialPrice(3, 10);
        Item banana = ItemObjectMother.itemWithSpecialPrice("banana", 12, specialPriceForBanana);
        Item cherry = ItemObjectMother.itemWithSpecialPrice("cherry", 3, specialPriceForCherry);

        itemUnitsMap.put(banana, 7);
        itemUnitsMap.put(cherry, 3);

        given(bargainService.getDiscount(itemUnitsMap)).willReturn(3);
        given(basket.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        int actual = receipt.getToPay();

        //then
        assertThat(actual, is(equalTo(expected)));
    }

}