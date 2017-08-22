package store.item;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    private SpecialPrice specialPrice;

}
