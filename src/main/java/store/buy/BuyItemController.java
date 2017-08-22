package store.buy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/buy")
public class BuyItemController {

    private final BuyService buyService;

    public BuyItemController(BuyService buyService) {
        this.buyService = buyService;
    }

    @PostMapping
    public ResponseEntity<Integer> addItems(
            @RequestBody BuyItemDTO buyItemDTO) {
        Integer totalPriceResponse = buyService.buy(buyItemDTO);
        return new ResponseEntity<>(totalPriceResponse, HttpStatus.ACCEPTED);
    }
}
