package br.com.bittreasure.impl.exceptions.issues;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ApiModel
public class Issue implements Serializable {

    @ApiModelProperty(example = "IllegalStateException")
    private String id;
    @ApiModelProperty(example = "No enum constant br.com.bittreasure.impl.coin.filters.ValidCoinFilters.ASD")
    private String message;

    public Issue(Exception e) {
        this.id = e.toString();
        this.message = e.getMessage();
    }
}
