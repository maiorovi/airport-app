package repository;

import domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	Passenger findById(Long id);
}
