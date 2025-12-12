package uz.billsplitter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.billsplitter.dto.request.BillRequestDto;
import uz.billsplitter.entity.Bill;
import uz.billsplitter.service.BillService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Optional<Bill> bill = billService.getBillById(id);
        return bill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody BillRequestDto requestDto) {
        Bill created = billService.createBill(requestDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody BillRequestDto requestDto) {
        Bill updated = billService.updateBill(id, requestDto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
