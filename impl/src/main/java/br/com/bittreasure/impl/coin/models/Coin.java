package br.com.bittreasure.impl.coin.models;

import com.couchbase.client.java.repository.annotation.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @JsonProperty("is_new")
    private String isNew;
    @ToString.Include
    @JsonProperty("is_active")
    private String isActive;
    private String type;
    private List<TeamMember> team;
    private List<Tag> tags;
    private String description;
    private String message;
    @JsonProperty("started_at")
    @ToString.Include
    private LocalDateTime startedAt;
    @JsonProperty("development_status")
    private String developmentStatus;
    @JsonProperty("hardware_wallet")
    private String hardwareWallet;
    @JsonProperty("proof_type")
    private String proofType;
    @JsonProperty("org_structure")
    private String orgStructure;
    @JsonProperty("hash_algorithm")
    private String hashAlgorithm;
    @JsonProperty("first_data_at")
    private LocalDateTime firstDataAt;
    @JsonProperty("last_data_at")
    private LocalDateTime lastDataAt;
    @JsonProperty("time_open")
    private LocalDateTime timeOpen;
    @JsonProperty("time_close")
    private LocalDateTime timeClose;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Long volume;
    @JsonProperty("market_cap")
    private Long marketCap;
    private Set<String> exchanges;

}