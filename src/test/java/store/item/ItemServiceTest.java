package store.item;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import store.stub.ItemObjectMother;
import store.utils.MapperHandler;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static store.stub.ItemObjectMother.item;
import static store.stub.ItemObjectMother.itemDTO;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemServiceTest {

    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private MapperHandler mapperHandler;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        itemService = new ItemService(itemRepository, mapperHandler);
        given(mapperHandler.transformTo(any(ItemDTO.class), any())).willReturn(item("banana", 3));
    }

    @Test
    public void addItem() throws Exception {
        ItemDTO item = itemDTO("banana", 3);

        itemService.addItem(item);

        List<ItemDTO> actual = itemService.getAllItems();
        assertNotNull(actual);


    }

    @Test
    public void getAllItems() throws Exception {
        List<ItemDTO> actual = itemService.getAllItems();
        // TODO: 04.09.17 lista jest nullem
        assertNotNull(actual);
    }

}