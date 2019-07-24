package br.com.bittreasure.impl.coin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    private String id;
    private String name;
    @JsonProperty("coin_counter")
    private String coinCounter;
    @JsonProperty("ico_counter")
    private String icoCounter;
}
