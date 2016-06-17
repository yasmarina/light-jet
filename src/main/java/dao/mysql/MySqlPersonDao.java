package dao.mysql;

import dao.interfaces.PersonDao;
import model.Person;

import java.sql.Types;
import java.time.LocalDate;
import java.util.Optional;

@FunctionalInterface
public interface MySqlPersonDao extends PersonDao {

    @Override
    default boolean add(Person person) {
        return withPreparedStatement(
                "INSERT INTO person " +
                        "(first_name, last_name, dob, email, password, address, phone) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                preparedStatement -> {
                    preparedStatement.setString(1, person.getEmail());
                    preparedStatement.setString(2, person.getFirstName());
                    preparedStatement.setString(3, person.getLastName());
                    LocalDate dob = person.getDob();
                    if (dob == null) {
                        preparedStatement.setNull(4, Types.DATE);
                    } else {
                        preparedStatement.setDate(4, java.sql.Date.valueOf(dob));
                    }
                    preparedStatement.setString(5, person.getPassword());
                    preparedStatement.setString(6, person.getAddress());
                    preparedStatement.setString(7, person.getPhone());
                    return preparedStatement.executeUpdate() == 1;
                }).getOrThrowUnchecked();
    }

    @Override
    default Optional<Person> getPersonById(int id) {
        return executeQuery(
                "SELECT first_name, last_name, dob, email, password, address, phone" +
                        " FROM Person WHERE id = " + id,
                rs -> !rs.next() ? null :
                        new Person(id,
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getDate("dob").toLocalDate(),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("address"),
                                rs.getString("phone"))).toOptional();
    }

    @Override
    default Optional<String> getPasswordByEmail(String email) {
        return executeQuery("SELECT password FROM Person WHERE email = '" + email + "'",
                rs -> rs.next() ? rs.getString("password") : null
        ).toOptional();
    }

    @Override
    default boolean setPasswordByEmail(String email, String password) {
        return withStatement(statement ->
                1 <= statement.executeUpdate(
                        "UPDATE Person SET password = '" + password + "' WHERE email = '" + email + "'")
        ).toOptional().orElse(false);
    }

    @Override
    default Optional<Person> getPersonByEmail(String email) {
        return executeQuery(
                "SELECT id, first_name, last_name, dob, password, address, phone" +
                        " FROM Person WHERE email = '" + email + "'",
                rs -> rs.next()
                        ?   new Person( rs.getInt("id"),
                                        rs.getString("first_name"),
                                        rs.getString("last_name"),
                                        rs.getDate("dob").toLocalDate(),
                                        email,
                                        rs.getString("password"),
                                        rs.getString("address"),
                                        rs.getString("phone"))
                        :   null
        ).toOptional();
    }

    @Override
    default boolean addRole(String email, String role) {
        return withPreparedStatement(
                "INSERT INTO roles (email, role) VALUES (?, ?)",
                preparedStatement -> {
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, role);
                    return preparedStatement.executeUpdate() == 1;
                }).getOrThrowUnchecked();
    }
}
