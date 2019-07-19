package br.com.bittreasure.impl.exchange.models;

import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Document
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Exchange {

    @Id
    private String id;
    @ToString.Include
    private String name;
    @ToString.Include
    private String description;
    private String active;
    @JsonProperty("website_status")
    private String websiteStatus;
    @JsonProperty("api_status")
    private String apiStatus;
    private String message;
    private Integer markets;
    private List<Market> allMarkets;

}
