package controller;

import domain.Flight;
import exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.FlightService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class FlightController {

	@Autowired
	private FlightService flightService;

	@RequestMapping(value = "flights", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addFlight(@RequestBody Flight flight, HttpServletResponse response, HttpServletRequest request) throws IOException {
		try {
		 	flightService.saveFlight(flight);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.setHeader("Location", request.getRequestURI()+"/"+flight.getFlightId());

		return new ResponseEntity(HttpStatus.CREATED);
	}

	@RequestMapping(value = "flights", method = RequestMethod.GET)
	public ResponseEntity getAllFlights() {
		try {
			return new ResponseEntity(flightService.retrieveAllFlights(), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "flights/{id}", method = RequestMethod.GET)
	public ResponseEntity getFlight(@PathVariable("id") Long id) {
		Flight flight = flightService.findFlight(id);

		if (flight == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(flight, HttpStatus.OK);
	}

	@RequestMapping(value = "flights/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteFlight(@PathVariable("id") String id) {
		try {
			flightService.deleteFlight(Long.valueOf(id));
		} catch (EntityNotFoundException ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "flights/{id}", method = RequestMethod.PUT)
	public ResponseEntity updateFlights(@PathVariable("id") String id, @RequestBody Flight flight) {
		 Optional<Flight> flightOptional = flightService.updateFlight(flight, Long.valueOf(id));

		 if (!flightOptional.isPresent()) {
		 	return new ResponseEntity(HttpStatus.NOT_FOUND);
		 }

		 return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
