package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String password;
    private String address;
    private String phone;

    public enum Role {
        ClIENT, ADMINISTRATOR
    }

    public Person(String firstName, String lastName, LocalDate dob, String email, String password, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

}
