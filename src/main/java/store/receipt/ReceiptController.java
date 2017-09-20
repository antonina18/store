package store.receipt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.item.Item;

import java.util.Map;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping
    public ResponseEntity<Map<Item, Integer>> getReceipt() {
        Map<Item, Integer> receipt = receiptService.getReceipt();
        return new ResponseEntity<>(receipt, HttpStatus.OK);
    }

//    @DeleteMapping
//    public ResponseEntity resetReceipt() {
//        receiptService.resetReceipt();
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
