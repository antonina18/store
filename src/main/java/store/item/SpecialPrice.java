package store.item;

public class SpecialPrice {

    private Integer unit;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialPrice that = (SpecialPrice) o;

        if (unit != null ? !unit.equals(that.unit) : that.unit != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;

    }

    @Override
    public int hashCode() {
        int result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("SpecialPrice{%d for %d}", +unit, price);
    }
}
