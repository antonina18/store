package store.item;

import lombok.Data;

@Data
public class ItemDTO {

    private String name;
    private Integer price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
