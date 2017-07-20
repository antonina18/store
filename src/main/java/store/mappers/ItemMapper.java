package store.mappers;

import org.springframework.stereotype.Component;
import store.dtos.ItemDto;
import store.dtos.SpecialPriceDto;
import store.models.Item;
import store.models.SpecialPrice;

@Component
public class ItemMapper {

    public ItemDto toDto(Item model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setPrice(model.getPrice());
        itemDto.setName(model.getName());
        if (model.getSpecialPrice() != null) {
            SpecialPriceDto specialPriceDto = new SpecialPriceDto();
            specialPriceDto.setId(model.getSpecialPrice().getId());
            specialPriceDto.setPrice(model.getSpecialPrice().getPrice());
            specialPriceDto.setUnit(model.getSpecialPrice().getUnit());
        }
        return itemDto;
    }

    public Item toEntity(ItemDto dto) {
        Item item = new Item();
        item.setPrice(dto.getPrice());
        item.setName(dto.getName());
        if (dto.getSpecialPrice() != null) {
            SpecialPrice specialPrice = new SpecialPrice();
            specialPrice.setPrice(dto.getSpecialPrice().getPrice());
            specialPrice.setUnit(dto.getSpecialPrice().getUnit());
            item.setSpecialPrice(specialPrice);
        }
        return item;
    }

}
