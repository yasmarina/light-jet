package dao.mysql;

import dao.interfaces.TicketDao;
import model.Flight;
import model.Person;
import model.Ticket;

import java.util.ArrayList;
import java.util.Collection;

@FunctionalInterface
public interface MySqlTicketDao extends TicketDao {

    @Override
    default boolean add(Ticket ticket) {
        return withPreparedStatement(
                "INSERT INTO ticket " +
                        "(client_id, flight_id, passport, first_name, last_name, luggage, priority, price) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                preparedStatement -> {
                    preparedStatement.setInt(1, ticket.getPerson().getId());
                    preparedStatement.setInt(2, ticket.getFlight().getId());
                    preparedStatement.setString(3, ticket.getPassport());
                    preparedStatement.setString(4, ticket.getFirstName());
                    preparedStatement.setString(5, ticket.getLastName());
                    preparedStatement.setInt(6, ticket.isLuggage() ? 1 : 0);
                    preparedStatement.setInt(7, ticket.isPriority() ? 1 : 0);
                    preparedStatement.setInt(8, ticket.getPrice());
                    return preparedStatement.executeUpdate() == 1;
                }).getOrThrowUnchecked();
    }

    default Collection<Ticket> getTicketsByClientEmail(String  email) {
        return executeQuery(
            "SELECT t.*, flight_id, departure_city, arrival_city, date, time " +
                    "FROM ticket t , flight f, person p " +
                    "WHERE f.id = flight_id and email = '" + email +"'",
                    rs -> {
            Collection<Ticket> tickets = new ArrayList<>();
            while (rs.next())
                tickets.add(new Ticket( rs.getInt("t.id"),
                                        new Flight( rs.getInt("flight_id"),
                                                rs.getString("departure_city"),
                                                rs.getString("arrival_city"),
                                                rs.getDate("date").toLocalDate(),
                                                rs.getTime("time").toLocalTime()),
                                        new Person(),
                                        rs.getString("first_name"),
                                        rs.getString("last_name"),
                                        rs.getString("passport"),
                                        rs.getBoolean("luggage"),
                                        rs.getBoolean("priority"),
                                        rs.getInt("price")));
                return tickets;
        }).getOrThrowUnchecked();
    }
}

/*    default Optional<Ticket> getTicketByClientEmail(String  email) {
        return executeQuery(
                "SELECT t.id, flight_id, client_id, from, to, date_time, seats_occupied, p.* " +
                        "FROM flight f, ticket t , person p" +
                        "WHERE flight_id = f.id and email = '" + email +"'",
                rs -> rs.next()
                        ? new Ticket(rs.getInt("t.id"),
                                    new Flight( rs.getInt("flight_id"),
                                                rs.getString("departure_city"),
                                                rs.getString("arrival_city"),
                                                rs.getDate("date").toLocalDate(),
                                                rs.getTime("time").toLocalTime(),
                                                rs.getInt("seats"),
                                                rs.getInt("price")),
                                    new Person( rs.getInt("client_id"),
                                                rs.getString("first_name"),
                                                rs.getString("last_name"),
                                                rs.getDate("dob").toLocalDate(),
                                                rs.getString("email"),
                                                rs.getString("password"),
                                                rs.getString("address"),
                                                rs.getString("phone")),
                                    rs.getString("passport"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getBoolean("luggage"),
                                    rs.getBoolean("priority"),
                                    rs.getInt("price"))
                        : null
        ).toOptional();
    }*/