package br.com.bittreasure.coin.models;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;

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

}