package br.com.bittreasure.contract.v1.coin.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel
@Data
@Builder
public class SimplifiedCoinResponse {

    @ApiModelProperty(example = "miota-iota")
    private String id;
    @ApiModelProperty(example = "IOTA")
    private String name;
    @ApiModelProperty(example = "true")
    @JsonProperty("is_active")
    private String isActive;
    @ApiModelProperty(example = "2013-04-28T00:00:00")
    @JsonProperty("last_data_at")
    private LocalDateTime lastDataAt;
}
