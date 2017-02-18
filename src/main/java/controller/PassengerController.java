package controller;


import domain.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import repository.PassengerRepository;

@Controller
public class PassengerController {
	@Autowired
	PassengerRepository passengerRepository;

	@RequestMapping(path="passengers")
	@ResponseBody
	public String requestMapping() {
		passengerRepository.save(new Passenger("Egor", "Maiorov"));
		return "[]";
	}
}
