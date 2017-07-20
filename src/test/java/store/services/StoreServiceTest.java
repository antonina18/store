package store.services;

import org.junit.Before;
import org.junit.Test;

public class StoreServiceTest {

    private StoreService storeService;

    @Before
    public void setUp(){
        storeService = new StoreService();
    }


    @Test
    public void getBasketContent() {
        //given
        Integer expected = 0;

        //when
        final Integer actual = storeService.getBasketContent();

        //then
//        assertThat(actual, is(equalTo(expected)));
    }

}