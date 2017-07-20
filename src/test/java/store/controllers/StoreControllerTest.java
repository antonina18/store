package store.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import store.services.StoreService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StoreController.class)
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Test
    public void mockMvcShouldBeInitialized() throws Exception {

        assertThat(mockMvc, is(notNullValue()));

    }

    @Test
    public void shouldReturn200okAnd0actualPrice() throws Exception {
        String expected = "{\"price\":0.0}";

        given(storeService.getBasketContent()).willReturn(0);

        mockMvc.perform(get("/store/receipt"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }



}
