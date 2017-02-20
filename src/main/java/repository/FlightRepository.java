package repository;

import domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long>{
	Flight findByFlightId(Long id);
}
