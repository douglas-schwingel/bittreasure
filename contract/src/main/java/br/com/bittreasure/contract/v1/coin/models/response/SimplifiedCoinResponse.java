package br.com.bittreasure.contract.v1.coin.models.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel
@Data
@Builder
public class SimplifiedCoinResponse {

    @ApiModelProperty(example = "miota-iota")
    private String id;
    @ApiModelProperty(example = "IOTA")
    private String name;
    @ApiModelProperty(example = "true")
    private String is_active;
}
