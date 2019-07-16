package br.com.bittreasure.coin.models;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Data
@ToString(onlyExplicitlyIncluded = true)
public class Coin {

    @Id
    @ToString.Include
    @Field
    private String id;

    @ToString.Include
    @Field
    private String name;

    @ToString.Include
    @Field
    private String symbol;

    @Field
    private int rank;

    @Field
    private String is_new;

    @Field
    @ToString.Include
    private String is_active;

    @Field
    private String type;

}