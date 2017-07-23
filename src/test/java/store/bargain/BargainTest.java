package store.bargain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import store.item.Bargain;
import store.item.Item;
import store.item.SpecialPrice;
import store.receipt.Receipt;
import store.stub.ItemObjectMother;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class BargainTest {

    @Mock
    private Receipt receipt;

    private Bargain bargain;

    @Before
    public void setUp(){
        this.bargain = new Bargain();
    }

    @Test
    public void shouldReturnReceiptWithAggregateItemsIncludingBargainAlthoughBuyDoubles() throws Exception {
        //given
        int expected = 3;
        SpecialPrice specialPriceForButter = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForBread = ItemObjectMother.specialPrice(3, 10);
        Item butter = ItemObjectMother.itemWithSpecialPrice("butter", 12, specialPriceForButter);
        Item bread = ItemObjectMother.itemWithSpecialPrice("bread", 3, specialPriceForBread);
        Map<Item, Integer> itemUnitsMap = new HashMap<>();
        itemUnitsMap.put(butter, 7);
        itemUnitsMap.put(bread, 3);
        itemUnitsMap.put(butter, 2);

        given(receipt.getItemUnitMap()).willReturn(itemUnitsMap);

        //when
        final Integer actual = bargain.getDiscount(receipt.getItemUnitMap());

        //then
        assertThat(actual, is(equalTo(expected)));
    }

}