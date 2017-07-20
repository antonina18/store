package store.models;

import java.util.Map;

public class Receipt {

    private Map<Integer, Item> values;
    private Integer totalPrice;

    public Map<Integer, Item> getValues() {
        return values;
    }

    public void setValues(Map<Integer, Item> values) {
        this.values = values;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
