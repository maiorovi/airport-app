package domain.valueobject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Route {
	@Column(name="from_airport")
	private String fromAirport;
	@Column(name="to_airport")
	private String toAirport;

	public Route() {
	}

	public Route(String fromAirport, String toAirport) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public String getToAirport() {
		return toAirport;
	}

	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Route route = (Route) o;

		if (fromAirport != null ? !fromAirport.equals(route.fromAirport) : route.fromAirport != null) return false;
		return toAirport != null ? toAirport.equals(route.toAirport) : route.toAirport == null;
	}

	@Override
	public int hashCode() {
		int result = fromAirport != null ? fromAirport.hashCode() : 0;
		result = 31 * result + (toAirport != null ? toAirport.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Route{" +
				"fromAirport='" + fromAirport + '\'' +
				", toAirport='" + toAirport + '\'' +
				'}';
	}
}
