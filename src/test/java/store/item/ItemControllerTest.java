package store.item;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import store.stub.ItemObjectMother;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private final Gson gson = new Gson();

    @Test
    public void mockMvcShouldBeInitialized() throws Exception {

        assertThat(mockMvc, is(notNullValue()));

    }

    @Test
    public void shouldReturn2XXAndActualPrice() throws Exception {
        String expected = "{\"actualTotalPrice\":15}";
        Item inputItem = ItemObjectMother.item("test", 5);

        given(itemService.addItems(3, inputItem)).willReturn(15);

        mockMvc.perform(
                post("/items")
                        .param("unit", "3")
                        .content(gson.toJson(inputItem))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expected));

    }



}
