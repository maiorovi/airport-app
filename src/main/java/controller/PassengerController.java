package controller;


import domain.Passenger;
import exceptions.EntityNotFoundException;
import exceptions.PassengerUpdateNotAllowedException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PassengerService;

import java.io.IOException;
import java.util.List;

@Controller
public class PassengerController {
	@Autowired
	private PassengerService passengerService;

	@RequestMapping(path="passengers", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Passenger>> requestMapping(@RequestParam(value = "start", required = false)String start,
														  @RequestParam(value = "pageSize", required = false) String pageSize) {
		if (start != null && pageSize != null) {
			PageRequest pageRequest	= new PageRequest(Integer.valueOf(start), Integer.valueOf(pageSize));
			Page<Passenger> passengerPage = passengerService.findAllUsingPage(pageRequest);

			return new ResponseEntity<List<Passenger>>(passengerPage.getContent(), HttpStatus.OK);
		}

		return new ResponseEntity<List<Passenger>>(passengerService.findAll(), HttpStatus.OK);
	}


	@RequestMapping(path = "passengers", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity savePassenger(@RequestBody Passenger passenger) throws IOException {
		JSONObject response = new JSONObject();
		passengerService.savePassenger(passenger);
		response.put("location", "passengers/" + passenger.getId());


		ResponseEntity responseEntity = new ResponseEntity(response.toMap(), HttpStatus.CREATED);

		return responseEntity;
	}

	@RequestMapping(path = "passengers/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Passenger> getPassenger(@PathVariable("id")String passengerId) {
		Passenger passenger = passengerService.retrievePassengerById(Long.valueOf(passengerId));

		if (passenger != null) {
			return new ResponseEntity(passenger,HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path = "passengers/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity deletePassenger(@PathVariable("id")String passengerId) {
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
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} catch (PassengerUpdateNotAllowedException ex) {
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
