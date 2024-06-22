package com.Receipt.Receipt.ReceiptController;

import com.Receipt.Receipt.Entity.Receipt;
import com.Receipt.Receipt.ReceiptService.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

        @Autowired
        private ReceiptService receiptService;

        @GetMapping
        public List<Receipt> getAllReceipts() {
            return receiptService.getAllReceipts();
        }

        @GetMapping("/{id}")
        public Optional<Receipt> getReceiptById(@PathVariable Long id) {
            return receiptService.getReceiptById(id);
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Receipt saveReceipt(@RequestBody Receipt receipt) {
            return receiptService.saveReceipt(receipt);
        }

        @DeleteMapping("/{id}")
        public void deleteReceipt(@PathVariable Long id) {
            receiptService.deleteReceipt(id);
        }
    }


