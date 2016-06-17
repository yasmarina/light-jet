package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private int id;
    private String departureCity;
    private String arrivalCity;
    private LocalDate date;
    private LocalTime time;
    private int seats;
    private int price;

    public Flight(int id, String departureCity, String arrivalCity, LocalDate date, LocalTime time) {
        this.id = id;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
        this.time = time;
    }
}
