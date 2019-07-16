package br.com.bittreasure.v1.exchange.models;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

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
    private String website_status;
    private String api_status;
    private String message;

}
