package br.com.bittreasure.impl.exchange.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market {

    private String pair;
    @JsonProperty("base_currency_id")
    private String baseCurrencyId;
    @JsonProperty("base_currency_name")
    private String baseCurrencyName;
    @JsonProperty("quote_currency_id")
    private String quoteCurrencyId;
    @JsonProperty("quote_currency_name")
    private String quoteCurrenyName;
    private String url;
    private String category;
    @JsonProperty("fee_type")
    private String feeType;
    private String outlier;
    private Map<String, Quote> quotes;
    @JsonProperty("reported_volume_24h_share")
    private Double reportedVolume24hShare;
    @JsonProperty("last_updated")
    private LocalDate lastUpdated;

}
