package uz.billsplitter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.billsplitter.constant.CommissionType;
import uz.billsplitter.dto.request.BillRequest;
import uz.billsplitter.dto.response.BillResponseDto;
import uz.billsplitter.service.BillService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/split")
    public ResponseEntity<BillResponseDto> splitBill(
            @Valid @RequestBody BillRequest request,
            @RequestParam CommissionType commissionType,
            @RequestParam long commissionValue
    ) {
        return ResponseEntity.ok(billService.split(request, commissionType, commissionValue));
    }

    @PostMapping("/create")
    public ResponseEntity<BillResponseDto> createBill(
            @Valid @RequestBody BillRequest request,
            @RequestParam CommissionType commissionType,
            @RequestParam long commissionValue
    ) {
        return ResponseEntity.ok(billService.create(request, commissionType, commissionValue));
    }

    @GetMapping
    public ResponseEntity<List<BillResponseDto>> getAllBills() {
        return ResponseEntity.ok(billService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDto> getBillById(@PathVariable Long id) {
        BillResponseDto bill = billService.findById(id);
        if (bill == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
