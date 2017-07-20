package store.dtos;

public class ItemDto {

    private Long id;
    private String name;
    private Integer price;
    private SpecialPriceDto specialPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public SpecialPriceDto getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(SpecialPriceDto specialPrice) {
        this.specialPrice = specialPrice;
    }
}
