package service;

import domain.Flight;
import exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.FlightRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FlightService {

	@Autowired
	private FlightRepository flightRepository;

	public void setFlightRepository(FlightRepository flightRepository) {
		this.flightRepository = flightRepository;
	}

	public void saveFlight(Flight flight) {
		flightRepository.save(flight);
	}

	public List<Flight> retrieveAllFlights() {
		return flightRepository.findAll();
	}

	public void deleteFlight(Long id) {
		Flight flight = flightRepository.findByFlightId(id);
		if (flight == null) {
			throw new EntityNotFoundException();
		}

		flightRepository.delete(id);
	}

	public Flight findFlight(Long id) {
		return flightRepository.findByFlightId(id);
	}

	public Optional<Flight> updateFlight(Flight newFlight, Long id) {
		Flight flight = flightRepository.findByFlightId(id);

		if (flight == null) {
			return Optional.empty();
		}

		flight.setFlightCode(newFlight.getFlightCode());
		flight.setRoute(newFlight.getRoute());

		flightRepository.save(flight);

		return Optional.of(flight);
	}
}
