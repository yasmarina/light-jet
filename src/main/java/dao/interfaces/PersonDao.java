package dao.interfaces;

import model.Person;

import java.util.Optional;

public interface PersonDao extends Dao {
    boolean add(Person person);
    Optional<Person> getPersonByEmail(String email);
    Optional<Person> getPersonById(int id);
    Optional<String> getPasswordByEmail(String email);
    boolean setPasswordByEmail(String userName, String pwdHash);

    boolean addRole(String email, String role);
}
