package br.com.bittreasure.impl.coin.models;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Map;

@Document
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Coin {

    @Id
    @ToString.Include
    private String id;

    @ToString.Include
    private String name;

    @ToString.Include
    private String symbol;

    private int rank;
    private String is_new;

    @ToString.Include
    private String is_active;

    private String type;

    private Map<String, Double> precos;

}