package hr.OSSAirline.dto;

import lombok.Data;

@Data
public class FlightDto {
    private String id;
    private String flightNumber;
    private AirportDto from;
    private AirportDto to;
    private AirplaneDto airplane;
    private java.sql.Date date;
    private java.sql.Time time;
    private String gate;
    private java.sql.Time duration;
}
