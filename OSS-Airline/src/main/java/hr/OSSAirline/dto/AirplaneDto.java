package hr.OSSAirline.dto;

import lombok.Data;

@Data
public class AirplaneDto {
    private String id;
    private String registration;
    private String model;
    private Integer firstSeats;
    private Integer businessSeats;
    private Integer economySeats;
}
