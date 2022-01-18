package com.vanguard.healthmeterws.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerState {

    private Boolean hasGreaterPerformance;

    private Boolean hasAdvisorAssigned;

}
