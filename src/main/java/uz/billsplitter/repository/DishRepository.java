package uz.billsplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.billsplitter.entity.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
