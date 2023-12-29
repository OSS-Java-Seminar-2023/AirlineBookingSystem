package hr.OSSAirline.dto;

import lombok.Data;

@Data
public class PassengerDto {
    private String id;
    private String name;
    private String surname;
    private String mail;
    private String PIN;
    private java.sql.Date DOB;
}
