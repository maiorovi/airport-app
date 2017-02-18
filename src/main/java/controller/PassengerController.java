package controller;


import domain.Passenger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.PassengerService;

import java.io.IOException;

@Controller
public class PassengerController {
	@Autowired
	private PassengerService passengerService;

	@RequestMapping(path="passengers", method = RequestMethod.GET)
	@ResponseBody
	public String requestMapping() {
		passengerService.savePassenger(new Passenger("Egor", "Maiorov"));
		return "[]";
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
	public ResponseEntity getPassenger(@PathVariable("id")String passengderId) {
		Passenger passenger = passengerService.retrievePassengerById(Long.valueOf(passengderId));

		if (passenger != null) {
			return new ResponseEntity(passenger,HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
