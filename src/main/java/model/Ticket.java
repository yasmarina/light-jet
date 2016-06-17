package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private int id;
    private Flight flight;
    private Person person;
    private String firstName;
    private String lastName;
    private String passport;
    private boolean luggage;
    private boolean priority;
    private int price;
    public static final int LUGGAGE_PRICE = 1000;
    public static final int PRIORITY_PRICE = 1000;


    public Ticket(Flight flight, Person person,
                  String firstName, String lastName, String passport,
                  boolean luggage, boolean priority, int price) {
        this.flight = flight;
        this.person = person;
        this.passport = passport;
        this.firstName = firstName;
        this.lastName = lastName;
        this.luggage = luggage;
        this.priority = priority;
        this.price = price;
    }
}


