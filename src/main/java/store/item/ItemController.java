package store.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> addItems(@RequestBody Item item) {
        SpecialPrice specialPrice = new SpecialPrice();
        specialPrice.setPrice(4);
        specialPrice.setUnit(4);
        item.setSpecialPrice(specialPrice);
        Integer unit = 4;
        final Integer actualTotalPrice = itemService.addItems(unit, item);
        Map<String,Integer> totalPriceResponse = Collections.singletonMap("actualTotalPrice", actualTotalPrice);
        return new ResponseEntity<>(totalPriceResponse, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.ACCEPTED);
    }

}
