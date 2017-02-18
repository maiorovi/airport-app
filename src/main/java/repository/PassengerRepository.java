package repository;

import domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	Passenger findById(Long id);
}
