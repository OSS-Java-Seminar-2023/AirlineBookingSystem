package hr.OSSAirline.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PurchaseDto {
    private String id;
    private UserDto user;
    private List<TicketDto> tickets = new ArrayList<>();
    private String paymentInfo;
    private Date paymentDate;
}
