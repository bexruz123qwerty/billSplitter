package uz.billsplitter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.billsplitter.constant.CommissionType;
import uz.billsplitter.dto.request.BillRequest;
import uz.billsplitter.dto.response.BillResponse;
import uz.billsplitter.dto.response.PersonalBillResponse;
import uz.billsplitter.entity.Bill;
import uz.billsplitter.entity.Item;
import uz.billsplitter.mapper.ItemMapper;
import uz.billsplitter.service.BillService;
import uz.billsplitter.repository.BillRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final ItemMapper itemMapper;
    private final BillRepository billRepository;

    @Override
    public BillResponse split(BillRequest request, CommissionType type, long commissionValue) {
        List<Item> items = request.getItems().stream()
                .map(itemMapper::toEntity)
                .toList();

        long total = items.stream().mapToLong(Item::getPrice).sum();

        long commission = switch (type) {
            case PERCENT -> total * commissionValue / 100;
            case FIXED -> commissionValue;
        };

        long finalTotal = total + commission;

        Map<String, Long> amountByPerson = items.stream()
                .collect(Collectors.groupingBy(
                        i -> i.getPerson().getName(),
                        Collectors.summingLong(Item::getPrice)
                ));

        List<PersonalBillResponse> personalBills = amountByPerson.entrySet().stream()
                .map(e -> {
                    double share = (double) e.getValue() / total;
                    long commissionPart = Math.round(commission * share);
                    return new PersonalBillResponse(e.getKey(), e.getValue() + commissionPart);
                })
                .toList();

        return new BillResponse(finalTotal, personalBills);
    }

    @Override
    public BillResponse create(BillRequest request, CommissionType type, long commissionValue) {
        BillResponse response = split(request, type, commissionValue);

        List<Item> items = request.getItems().stream()
                .map(itemMapper::toEntity)
                .toList();

        Bill bill = new Bill(null, items, response.totalAmount());
        billRepository.save(bill);

        return response;
    }

    @Override
    public List<BillResponse> findAll() {
        return billRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public BillResponse findById(Long id) {
        return billRepository.findById(id)
                .map(this::toResponse)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);
    }

    private BillResponse toResponse(Bill bill) {
        Map<String, Long> amountByPerson = bill.getItems().stream()
                .collect(Collectors.groupingBy(
                        i -> i.getPerson().getName(),
                        Collectors.summingLong(Item::getPrice)
                ));

        List<PersonalBillResponse> personalBills = amountByPerson.entrySet().stream()
                .map(e -> new PersonalBillResponse(e.getKey(), e.getValue()))
                .toList();

        return new BillResponse(bill.getTotalAmount(), personalBills);
    }
}
