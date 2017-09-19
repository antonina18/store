package store.receipt;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
public class ReceiptService {

    private final Receipt receipt;

}
