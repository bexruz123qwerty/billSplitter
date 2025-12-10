package uz.billsplitter.service;

import uz.billsplitter.constant.CommissionType;
import uz.billsplitter.dto.request.BillRequest;
import uz.billsplitter.dto.response.BillResponseDto;

import java.util.List;

public interface BillService {
    BillResponseDto split(BillRequest request, CommissionType type, long commissionValue);

    BillResponseDto create(BillRequest request, CommissionType type, long commissionValue);

    List<BillResponseDto> findAll();

    BillResponseDto findById(Long id);

    void delete(Long id);
}
