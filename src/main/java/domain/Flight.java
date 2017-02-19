package domain;

import domain.valueobject.Route;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Flight {

	@Id
	@GeneratedValue
	@Column(name = "flight_id")
	private Long flightId;
	@Column(name = "flight_code")
	private String flightCode;
	@Embedded
	@Column(name = "route")
	private Route route;
//	@OneToMany(cascade = )
//	private Set<Passenger> registeredPassengers;

	public Flight() {
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
}
