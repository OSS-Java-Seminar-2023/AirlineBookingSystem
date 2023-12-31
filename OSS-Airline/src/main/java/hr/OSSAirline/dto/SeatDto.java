package hr.OSSAirline.dto;

import lombok.Data;

@Data
public class SeatDto {
        private String id;
        private FlightDto flight;
        private Float seatPrice;
        private String seatClass;
        private String seatNumber;
}
