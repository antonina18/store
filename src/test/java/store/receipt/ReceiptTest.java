package store.receipt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import store.item.Item;
import store.item.SpecialPrice;
import store.stub.ItemObjectMother;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static store.stub.ItemObjectMother.item;

@RunWith(SpringRunner.class)
public class ReceiptTest {

    private Receipt receipt;

    @Before
    public void setUp(){
        this.receipt = new Receipt();
    }

    @Test
    public void shouldReturnNormalPrice() throws Exception {
        //given
        int expected = 84;
        Item item = item("masło", 12);
        //when
        receipt.addItems(7, item);
        int actual = receipt.getTotalPrice();

        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnNormalPriceAlthoughGivenSpecialPrice() throws Exception {
        //given
        int expected = 24;
        SpecialPrice specialPrice = ItemObjectMother.specialPrice(3, 30);
        Item item = ItemObjectMother.itemWithSpecialPrice("butter", 12, specialPrice);

        //when
        receipt.addItems(2, item);
        int actual = receipt.getTotalPrice();

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void shouldReturnReceiptIncludingBargain() throws Exception {
        //given
        int expected = 72;
        SpecialPrice specialPrice = ItemObjectMother.specialPrice(3, 30);
        Item item = ItemObjectMother.itemWithSpecialPrice("butter", 12, specialPrice);
        //when
        receipt.addItems(7, item);
        int actual = receipt.getTotalPrice();

        //then
        assertThat(actual, is(equalTo(expected)));
    }
    @Test
    public void shouldReturnReceiptWithAggregateItemsIncludingBargain() throws Exception {
        //given
        int expected = 100;
        SpecialPrice specialPriceForButter = ItemObjectMother.specialPrice(3, 30);
        SpecialPrice specialPriceForBread = ItemObjectMother.specialPrice(3, 10);
        Item butter = ItemObjectMother.itemWithSpecialPrice("butter", 12, specialPriceForButter);
        Item bread = ItemObjectMother.itemWithSpecialPrice("bread", 3, specialPriceForBread);

        //when
        receipt.addItems(7, butter);
        receipt.addItems(3, bread);
        receipt.addItems(2, butter);
        int actual = receipt.getTotalPrice();

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void testReset() throws Exception {
        //given
        int expected = 0;
        Item item = item("masło", 12);
        //when
        receipt.addItems(7, item);
        receipt.reset();
        int actual = receipt.getTotalPrice();

        //then
        assertThat(actual, is(equalTo(expected)));
    }
}