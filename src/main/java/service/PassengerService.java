package service;

import domain.Passenger;
import exceptions.NonExistentPassengerException;
import exceptions.PassengerUpdateNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PassengerRepository;

import java.util.List;

@Service
@Transactional
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

	public void removePassenger(Long passengerId) {
		Passenger passenger = passengerRepository.findById(passengerId);

		if (passenger == null) {
			throw new NonExistentPassengerException();
		}

		passengerRepository.delete(passengerId);
	}

	public void updatePassenger(Long id, Passenger newPassenger) {
		Passenger passenger = passengerRepository.findById(id);
		if (!isUpdateAllowed(passenger)) {
			throw new PassengerUpdateNotAllowedException();
		}

		if (passenger == null) {
			throw new NonExistentPassengerException();
		}

		passenger.setFirstName(newPassenger.getFirstName());
		passenger.setLastName(newPassenger.getLastName());

		passengerRepository.save(passenger);
	}

	public List<Passenger> findAll() {
		return passengerRepository.findAll();
	}

	public boolean isUpdateAllowed(Passenger passenger) {
		return true;
	}
}
