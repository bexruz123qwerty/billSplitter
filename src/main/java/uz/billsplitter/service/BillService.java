package uz.billsplitter.service;

import uz.billsplitter.constant.CommissionType;
import uz.billsplitter.dto.request.BillRequest;
import uz.billsplitter.dto.response.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse split(BillRequest request, CommissionType type, long commissionValue);

    BillResponse create(BillRequest request, CommissionType type, long commissionValue);

    List<BillResponse> findAll();

    BillResponse findById(Long id);

    void delete(Long id);
}
