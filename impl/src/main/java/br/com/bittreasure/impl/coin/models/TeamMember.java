package br.com.bittreasure.impl.coin.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMember {

    @ApiModelProperty(example = "satoshi-nakamoto")
    private String id;
    @ApiModelProperty(example = "Satoshi Nakamoto")
    private String name;
    @ApiModelProperty(example = "Founder")
    private String position;

}
