package br.com.bittreasure.contract.v1.coin.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ApiModel
public class CoinResponse {

    @ApiModelProperty(value = "id", example = "miota-iota")
    @ToString.Include
    private String id;

    @ApiModelProperty(value = "name", example = "IOTA")
    @ToString.Include
    private String name;

    @ApiModelProperty(value = "symbol", example = "MIOTA")
    private String symbol;
    @ApiModelProperty(value = "rank", example = "1")
    private int rank;
    @ApiModelProperty(value = "is_new", example = "false")
    private String is_new;
    @ApiModelProperty(value = "is_active", example = "true")
    private String is_active;
    @ApiModelProperty(value = "type", example = "coin")
    private String type;
}
