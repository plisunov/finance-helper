package com.vanguard.healthmeterws.controller;

import com.vanguard.healthmeterws.exception.FinanceHelperException;
import com.vanguard.healthmeterws.model.CustomerState;
import com.vanguard.healthmeterws.service.ClientHealthChecker;
import org.springframework.web.bind.annotation.*;

@RestController
public class HServiceController {

    private final ClientHealthChecker clientHealthChecker;

    public HServiceController(ClientHealthChecker clientHealthChecker) {
        this.clientHealthChecker = clientHealthChecker;
    }

    @GetMapping
    @CrossOrigin
    CustomerState getClientHealthStatus(@RequestParam(name = "poid",required = true) String poid) throws FinanceHelperException {
        return clientHealthChecker.getClientHealthStatus(poid);
    }

    @PostMapping
    @CrossOrigin
    void setClientAdvisor(@RequestParam(name = "poid",required = true) String poid) throws FinanceHelperException {
        clientHealthChecker.setAdvisor(poid);
    }


}
