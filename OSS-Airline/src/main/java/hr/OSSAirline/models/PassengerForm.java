package hr.OSSAirline.models;

import hr.OSSAirline.dto.PassengerDto;
import lombok.Data;

import java.util.List;

@Data
public class PassengerForm {
    private List<PassengerDto> passengers;
}
