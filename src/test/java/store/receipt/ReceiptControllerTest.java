package store.receipt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReceiptControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(wac).build();
    }


    @Test
    public void mockMvcShouldBeInitialized() throws Exception {
        assertThat(mockMvc, is(notNullValue()));
    }

    @Test
    public void shouldReturn2XXAndActualPrice() throws Exception {
        String expected = "\"toPay\":57";

        mockMvc.perform(
                post("/items")
                        .param("unit", "3")
                        .content("{\"name\":\"butter\",\"price\":10}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                post("/items")
                        .param("unit", "3")
                        .content("{\"name\":\"bread\",\"price\":10}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(
                get("/receipt"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(expected)));
    }

}
