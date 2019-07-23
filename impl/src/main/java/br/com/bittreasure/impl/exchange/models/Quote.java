package br.com.bittreasure.impl.exchange.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    private Double price;
    @JsonProperty("volume_24h")
    private Double volume24h;
}
