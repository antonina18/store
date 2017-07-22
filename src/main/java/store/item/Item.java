package store.item;

public class Item {

    private String name;
    private Integer price;

    private SpecialPrice specialPrice;

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

    public SpecialPrice getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(SpecialPrice specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        return specialPrice != null ? specialPrice.equals(item.specialPrice) : item.specialPrice == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (specialPrice != null ? specialPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
