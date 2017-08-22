package store.item;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class SpecialPrice {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer unit;
    private Integer price;

}
