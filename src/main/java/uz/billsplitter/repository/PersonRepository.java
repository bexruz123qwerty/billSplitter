package uz.billsplitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.billsplitter.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
