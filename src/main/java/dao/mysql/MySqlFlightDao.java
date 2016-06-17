package dao.mysql;

import dao.interfaces.FlightDao;
import model.Flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@FunctionalInterface
public interface MySqlFlightDao extends FlightDao {

    @Override
    default Collection<String> getCityList(){
        return executeQuery(
                "SELECT departure_city FROM flight GROUP BY departure_city",
                rs -> {
                    Collection<String> cities = new ArrayList<>();
                    while (rs.next())
                        cities.add(rs.getString("departure_city"));
                    return cities;
                }).getOrThrowUnchecked();
    }

    @Override
    default Optional<Flight> getFlightById(int id) {
        return executeQuery(
                "SELECT departure_city, arrival_city, date, time, seats, price FROM flight WHERE id = " + id,
                rs -> rs.next()
                        ? new Flight(   id,
                                        rs.getString("departure_city"),
                                        rs.getString("arrival_city"),
                                        rs.getDate("date").toLocalDate(),
                                        rs.getTime("time").toLocalTime(),
                                        rs.getInt("seats"),
                                        rs.getInt("price"))
                        : null
        ).toOptional();
    }

    @Override
    default Collection<Flight> getFlightByDirection(String departureCity, String arrivalCity) {
        return executeQuery(
                "SELECT * FROM flight " +
                        "WHERE departure_city = '" + departureCity + "' AND arrival_city = '" + arrivalCity + "'",
                rs -> {
                    Collection<Flight> flights = new ArrayList<>();
                    while (rs.next())
                        flights.add(new Flight( rs.getInt("id"),
                                                departureCity,
                                                arrivalCity,
                                                rs.getDate("date").toLocalDate(),
                                                rs.getTime("time").toLocalTime(),
                                                rs.getInt("seats"),
                                                rs.getInt("price")));
                    return flights;
                }).getOrThrowUnchecked();
    }
/*    @Override
    default Collection<Flight> getAll() {
        return executeQuery(
                "SELECT * FROM flight",
                rs -> {
                    Collection<Flight> flights = new HashSet<>();
                    while (rs.next())
                        flights.add(new Flight( rs.getInt("id"),
                                                rs.getString("departure_city"),
                                                rs.getString("arrival_city"),
                                                rs.getDate("date").toLocalDate(),
                                                rs.getTime("time").toLocalTime(),
                                                rs.getInt("seats"),
                                                rs.getInt("price")));
                    return flights;
                }).getOrThrowUnchecked();
    }*/
}
