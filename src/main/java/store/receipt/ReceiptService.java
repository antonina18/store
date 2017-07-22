package store.receipt;

import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final Receipt receipt;

    public ReceiptService(Receipt receipt) {
        this.receipt = receipt;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void resetReceipt() {
        receipt.reset();
    }

}
