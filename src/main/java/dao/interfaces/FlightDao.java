package dao.interfaces;

import model.Flight;

import java.util.Collection;
import java.util.Optional;

public interface FlightDao extends Dao {
    Collection<String> getCityList();
    Optional<Flight> getFlightById(int id);
    Collection<Flight> getFlightByDirection(String departureCity, String arrivalCity);
//    Collection<Flight> getAll();
}
