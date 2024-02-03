package hr.OSSAirline.models;

import lombok.Data;

import java.sql.Date;
import java.time.LocalTime;

@Data
public class FlightForm {
    public String flightNumber;
    public String fromAirportId;
    public String toAirportId;
    public String airplaneId;
    public Date date;
    public LocalTime time;
    public String gate;
    public LocalTime duration;
    public Double firstClassPrice;
    public Double businessClassPrice;
    public Double economyClassPrice;
}