package store.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Integer>> addItems(
            @RequestParam Integer unit,
            @RequestBody Item item
    ) {
        final Integer actualTotalPrice = itemService.addItems(unit, item);
        Map<String,Integer> totalPriceResponse = Collections.singletonMap("actualTotalPrice", actualTotalPrice);
        return new ResponseEntity<>(totalPriceResponse, HttpStatus.ACCEPTED);
    }

}
