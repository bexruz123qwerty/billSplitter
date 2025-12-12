package uz.billsplitter.service;

import uz.billsplitter.dto.request.BillRequestDto;
import uz.billsplitter.dto.response.BillResponseDto;
import uz.billsplitter.entity.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {

    List<Bill> getAllBills();

    Optional<Bill> getBillById(Long billId);

    Bill createBill(BillRequestDto billRequestDto);

    Bill updateBill(Long billId, BillRequestDto billRequestDto);

    void deleteBill(Long billId);

    BillResponseDto splitBill(Long billId);
}
