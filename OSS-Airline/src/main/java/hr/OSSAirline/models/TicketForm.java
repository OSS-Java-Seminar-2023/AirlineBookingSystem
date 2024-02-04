package hr.OSSAirline.models;

import hr.OSSAirline.dto.TicketDto;
import lombok.Data;

import java.util.List;

@Data
public class TicketForm {
    private List<TicketDto> tickets;
    private List<String> passenger;
    private List<String> flight;
    private List<String> seat;
    private List<Float> price;
}
