package service;

import domain.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PassengerRepository;

import java.util.List;

@Service
public class PassengerService {
	@Autowired
	private PassengerRepository passengerRepository;

	public List<Passenger> loadAllPassengers() {
		return passengerRepository.findAll();
	}

	public void savePassenger(Passenger passenger) {
		passengerRepository.save(passenger);
	}

	public Passenger retrievePassengerById(Long id) {
		return passengerRepository.findById(id);
	}
}
