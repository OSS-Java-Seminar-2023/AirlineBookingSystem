package hr.OSSAirline.controllers;

import hr.OSSAirline.models.Purchase;
import hr.OSSAirline.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseController {

    public final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseController(PurchaseRepository purchaseRepository){
        this.purchaseRepository=purchaseRepository;
    }

}
