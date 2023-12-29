package hr.OSSAirline.dto;


import lombok.Data;

@Data
public class TicketDto {
    private String id;
    private PassengerDto passenger;
    private FlightDto flight;
    private SeatDto seat;
    private Float ticketPrice;
}
