package com.vanguard.healthmeterws.configuration;

import com.vanguard.healthmeterws.model.MarketState;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class MarketHealthStateImpl implements MarketHealthState {

    @Value("${app.gateway.url}")
    private String apiGateway;

    @Autowired
    private RestTemplate restTemplate;

    private static final String SP_PATH = "sp";
    private static final String NASDAQ_PATH = "nasdaq";
    private static final String DJIA_PATH = "djia";

    private Double spState;

    private Double nasdaqState;

    private Double djiaState;

    @Override
    public MarketState getMarketHealth() {
        return MarketState.builder()
                .spState(spState)
                .nasdaqState(nasdaqState)
                .djiaState(djiaState)
                .build();
    }

    @PostConstruct
    public void init() {
        getSPStatus();
        getNasdaqStatus();
        getDJIAStatus();
    }

    @SneakyThrows
    private void getDJIAStatus() {
        String stateAsString = restTemplate.getForObject(apiGateway + DJIA_PATH, String.class);
        if (StringUtils.hasLength(stateAsString)) {
            djiaState = Double.parseDouble(stateAsString);
        }
    }

    @SneakyThrows
    private void getNasdaqStatus() {
        String stateAsString = restTemplate.getForObject(apiGateway + NASDAQ_PATH, String.class);
        if (StringUtils.hasLength(stateAsString)) {
            nasdaqState = Double.parseDouble(stateAsString);
        }
    }

    @SneakyThrows
    private void getSPStatus() {
        String stateAsString = restTemplate.getForObject(apiGateway + SP_PATH, String.class);
        if (StringUtils.hasLength(stateAsString)) {
            spState = Double.parseDouble(stateAsString);
        }
    }
}
