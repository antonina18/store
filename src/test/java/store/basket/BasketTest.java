package store.basket;

import org.junit.Before;
import org.junit.Test;
import store.item.Item;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static store.stub.ItemObjectMother.item;

public class BasketTest {

    private Basket basket;

    @Before
    public void setUp(){
        basket = new Basket();
    }

    @Test
    public void addItem() throws Exception {
        //given
        Map<Item, Integer> expected = new HashMap<>();
        Item item = item("banana", 12);
        expected.put(item, 3);

        //when
        basket.add(item, 3);
        Map<Item, Integer> actual = basket.getItemUnitMap();

        //then
        assertThat(actual, is(equalTo(expected)));
    }


    @Test
    public void addFewItems() throws Exception {
        //given
        Map<Item, Integer> expected = new HashMap<>();
        Item banana = item("banana", 12);
        Item cherry = item("cherry", 3);
        expected.put(banana, 3);
        expected.put(cherry, 2);

        //when
        basket.add(banana, 3).add(cherry, 2);
        Map<Item, Integer> actual = basket.getItemUnitMap();

        //then
        assertThat(actual, is(equalTo(expected)));
    }


    @Test
    public void addFewItemsNotInOrder() throws Exception {
        //given
        Map<Item, Integer> expected = new HashMap<>();
        Item banana = item("banana", 12);
        Item cherry = item("cherry", 3);
        expected.put(banana, 6);
        expected.put(cherry, 2);

        //when
        basket.add(banana, 3).add(cherry, 2).add(banana, 3);

        Map<Item, Integer> actual = basket.getItemUnitMap();

        //then
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    public void addSomeMoreItemsNotInOrder() throws Exception {
        //given
        Map<Item, Integer> expected = new HashMap<>();
        Item banana = item("banana", 12);
        Item cherry = item("cherry", 3);
        Item cocos = item("cocos", 6);
        expected.put(banana, 6);
        expected.put(cherry, 2);
        expected.put(cocos, 5);

        //when
        basket.add(banana, 3).add(cocos, 3).add(cherry, 2).add(cocos, 2).add(banana, 3);


        Map<Item, Integer> actual = basket.getItemUnitMap();

        //then
        assertThat(actual, is(equalTo(expected)));
    }

}