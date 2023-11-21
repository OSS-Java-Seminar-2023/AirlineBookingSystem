package hr.OSSAirline.controllers;

import hr.OSSAirline.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PurchaseController {

    public final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository){

        this.purchaseRepository=purchaseRepository;
    }

}
