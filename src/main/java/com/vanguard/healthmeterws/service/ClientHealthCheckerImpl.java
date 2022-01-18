package com.vanguard.healthmeterws.service;

import com.vanguard.healthmeterws.configuration.MarketHealthState;
import com.vanguard.healthmeterws.entity.CustomerEntity;
import com.vanguard.healthmeterws.exception.FinanceHelperException;
import com.vanguard.healthmeterws.model.CustomerState;
import com.vanguard.healthmeterws.model.MarketState;
import com.vanguard.healthmeterws.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientHealthCheckerImpl implements ClientHealthChecker {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MarketHealthState marketHealthState;

    @Override
    public CustomerState getClientHealthStatus(String poid) throws FinanceHelperException {
        try {
            Long id = Long.parseLong(poid);
            Optional<CustomerEntity> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                throw new FinanceHelperException("Client not found by poid");
            }
            return checkCustomerFinanceHelp(customer.get());
        } catch (NumberFormatException e) {
            throw new FinanceHelperException("Client poid is invalid");
        }
    }

    private CustomerState checkCustomerFinanceHelp(CustomerEntity customerEntity) {
        Double marketPerformance = customerEntity.getMarketPerformance();
        MarketState marketHealth = marketHealthState.getMarketHealth();
        return CustomerState.builder()
                .hasGreaterPerformance(marketPerformance >= marketHealth.getDjiaState() &&
                        marketPerformance >= marketHealth.getNasdaqState() &&
                        marketPerformance >= marketHealth.getSpState())
                .hasAdvisorAssigned(customerEntity.hasAdvisorAssigned != null ? customerEntity.hasAdvisorAssigned : false)
                .build();
    }

    @Override
    @Transactional
    public void setAdvisor(String poid) throws FinanceHelperException {
        try {
            Long id = Long.parseLong(poid);
            Optional<CustomerEntity> customer = customerRepository.findById(id);
            if (customer.isPresent()) {
                customer.get().setHasAdvisorAssigned(true);
            } else {
                throw new FinanceHelperException("Client not found by poid");
            }
        } catch (NumberFormatException e) {
            throw new FinanceHelperException("Client poid is invalid");
        }

    }
}
