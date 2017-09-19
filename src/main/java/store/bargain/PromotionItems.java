package store.bargain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
public class PromotionItems implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String itemX;
    private String itemY;
    private Integer discount;

    public PromotionItems() {

    }

    public PromotionItems(String itemX, String itemY) {
        this.itemX = itemX;
        this.itemY = itemY;
    }

    public void setItemX(String itemX) {
        this.itemX = itemX;
    }

    public void setItemY(String itemY) {
        this.itemY = itemY;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
