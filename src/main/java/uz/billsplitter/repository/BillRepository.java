package uz.billsplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.billsplitter.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
