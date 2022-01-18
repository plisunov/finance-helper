package com.vanguard.healthmeterws.service;

import com.vanguard.healthmeterws.exception.FinanceHelperException;
import com.vanguard.healthmeterws.model.CustomerState;

public interface ClientHealthChecker {

    CustomerState getClientHealthStatus(String poid) throws FinanceHelperException;

    void setAdvisor(String poid) throws FinanceHelperException;
}
