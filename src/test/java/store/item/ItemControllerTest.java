package store.item;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static store.stub.ItemObjectMother.item;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    @Test
    public void mockMvcShouldBeInitialized() throws Exception {
        assertThat(mockMvc, is(notNullValue()));
    }

    @Test
    public void shouldReturn2XXAndActualPrice() throws Exception {
        String expected = "{\"actualTotalPrice\":15}";

        given(itemService.addItems(eq(3), eq(item("A", 10)))).willReturn(15);

        mockMvc.perform(
                post("/items")
                        .param("unit", "3")
                        .content("{\"name\":\"A\",\"price\":10}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expected));

    }



}
