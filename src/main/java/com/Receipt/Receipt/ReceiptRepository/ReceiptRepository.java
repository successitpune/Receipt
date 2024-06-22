package com.Receipt.Receipt.ReceiptRepository;

import com.Receipt.Receipt.Entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {

}
