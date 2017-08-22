package store.bargain;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotionItems")
public class PromotionItemsController {

    private final BargainService bargainService;

    public PromotionItemsController(BargainService bargainService) {
        this.bargainService = bargainService;
    }

    @PostMapping
    public ResponseEntity addPromotionItems(
            @RequestBody PromotionItemDTO promotionItem) {
        bargainService.addPromotion(promotionItem);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PromotionItemDTO>> getPromotionItems() {
        List<PromotionItemDTO> promotionItems = bargainService.getPromotionItems();
        return new ResponseEntity(promotionItems, HttpStatus.OK);
    }
}
