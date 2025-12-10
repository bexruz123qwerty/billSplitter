package uz.billsplitter.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.billsplitter.constant.CommissionType;
import uz.billsplitter.dto.request.BillRequest;
import uz.billsplitter.dto.response.BillResponse;
import uz.billsplitter.service.BillService;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/calculate")
    public ResponseEntity<BillResponse> calculateBill(
            @Valid @RequestBody BillRequest request,
            @RequestParam CommissionType commissionType,
            @RequestParam long commissionValue
    ) {
        BillResponse response = billService.calculate(request, commissionType, commissionValue);
        return ResponseEntity.ok(response);
    }
}
