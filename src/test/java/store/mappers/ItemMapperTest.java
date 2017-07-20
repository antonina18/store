package store.mappers;

import org.junit.Before;
import org.junit.Test;
import store.dtos.ItemDto;
import store.models.Item;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static store.stub.ItemObjectMother.item;
import static store.stub.ItemObjectMother.itemDto;

public class ItemMapperTest {

    private ItemMapper itemMapper;

    @Before
    public void setUp(){
        this.itemMapper = new ItemMapper();
    }

    @Test
    public void shouldMapItemDtoToItemModel(){
        //given
        final ItemDto itemDto = itemDto("masło", 7);

        //when
        final Item itemModel = itemMapper.toEntity(itemDto);

        //then
        assertThat(itemModel.getName(), is(equalTo(itemDto.getName())));
        assertThat(itemModel.getPrice(), is(equalTo(itemDto.getPrice())));
    }

    @Test
    public void shouldMapItemModelToItemDto(){
        //given
        final Item itemModel = item("masło", 7);

        //when
        final ItemDto itemDto = itemMapper.toDto(itemModel);

        //then
        assertThat(itemDto.getName(), is(equalTo(itemModel.getName())));
        assertThat(itemDto.getPrice(), is(equalTo(itemModel.getPrice())));
    }

}