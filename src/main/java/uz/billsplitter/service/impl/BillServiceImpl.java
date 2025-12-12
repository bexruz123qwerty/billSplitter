package uz.billsplitter.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.billsplitter.dto.request.BillRequestDto;
import uz.billsplitter.dto.response.BillResponseDto;
import uz.billsplitter.entity.Bill;
import uz.billsplitter.mapper.BillMapper;
import uz.billsplitter.mapper.PersonMapper;
import uz.billsplitter.repository.BillRepository;
import uz.billsplitter.repository.PersonRepository;
import uz.billsplitter.service.BillService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillMapper billMapper;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> getBillById(Long billId) {
        return Optional.ofNullable(billRepository.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found")));
    }

    @Override
    @Transactional
    public Bill createBill(BillRequestDto billRequestDto) {
        Bill bill = billMapper.toEntity(billRequestDto);
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public Bill updateBill(Long billId, BillRequestDto billRequestDto) {
        Optional<Bill> optionalBill = getBillById(billId);
        if (optionalBill.isEmpty()) return null;

        Bill bill = optionalBill.get();
        Bill updatedBill = billMapper.toEntity(billRequestDto);

        bill.setServiceFeePercent(updatedBill.getServiceFeePercent());
        bill.setPeople(updatedBill.getPeople());
        bill.setDishes(updatedBill.getDishes());

        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public void deleteBill(Long billId) {
        Optional<Bill> optionalBill = getBillById(billId);
        optionalBill.ifPresent(billRepository::delete);
    }

    @Override
    public BillResponseDto splitBill(Long billId) {
        return null;
    }
}
