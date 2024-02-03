package hr.OSSAirline.controllers;

import hr.OSSAirline.dto.SeatDto;
import hr.OSSAirline.repositories.TicketRepository;
import hr.OSSAirline.services.TicketService;
import hr.OSSAirline.utils.SecurityCheck;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

}
