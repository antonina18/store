package store.bargain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PromotionItems implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String itemX;
    private String itemY;
    private Integer discount;

    public PromotionItems(String itemX, String itemY) {
        this.itemX = itemX;
        this.itemY = itemY;
    }

}
