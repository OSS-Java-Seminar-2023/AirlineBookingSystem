package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;
}
