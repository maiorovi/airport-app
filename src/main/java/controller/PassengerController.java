package controller;


import domain.Passenger;
import exceptions.EntityNotFoundException;
import exceptions.PassengerUpdateNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PassengerService;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
public class PassengerController {
	@Autowired
	private PassengerService passengerService;

	@RequestMapping(path = "passengers", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Passenger>> requestMapping(@RequestParam(value = "start", required = false) String start,
	                                                      @RequestParam(value = "pageSize", required = false) String pageSize) {
		if (start != null && pageSize != null) {
			PageRequest pageRequest = new PageRequest(Integer.valueOf(start), Integer.valueOf(pageSize));
			Page<Passenger> passengerPage = passengerService.findAllUsingPage(pageRequest);

			return ResponseEntity
					.ok()
					.body(passengerPage.getContent());

		}

		return ResponseEntity
				.ok()
				.body(passengerService.loadAllPassengers());
	}


	@RequestMapping(path = "passengers", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity savePassenger(@RequestBody Passenger passenger) throws IOException, URISyntaxException {
		passengerService.savePassenger(passenger);

		return ResponseEntity
				.created(new URI("passengers/"+passenger.getId()))
				.build();
	}

	@RequestMapping(path = "passengers/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Passenger> getPassenger(@PathVariable("id") String passengerId) {
		Optional<Passenger> passengerOptional = passengerService.retrievePassengerById(Long.valueOf(passengerId));

		return passengerOptional.map( p -> ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)).body(p)).orElse(ResponseEntity.notFound().build());
	}

	@RequestMapping(path = "passengers/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity deletePassenger(@PathVariable("id") String passengerId) {
		try {
			passengerService.removePassenger(Long.valueOf(passengerId));
		} catch (EntityNotFoundException ex) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}


	@RequestMapping(path = "passengers/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity updatePassenger(@PathVariable("id") String passengerId, @RequestBody Passenger passenger) {
		try {
			passengerService.updatePassenger(Long.valueOf(passengerId), passenger);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (PassengerUpdateNotAllowedException ex) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		return ResponseEntity.noContent().build();
	}

}
