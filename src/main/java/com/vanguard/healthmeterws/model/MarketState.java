package com.vanguard.healthmeterws.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MarketState {

    private Double spState;

    private Double nasdaqState;

    private Double djiaState;

}
