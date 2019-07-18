package br.com.bittreasure.impl.exceptions.errors;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ResponseError {

    @ApiModelProperty(example = "/v2/not_found")
    private String namespace;
    @ApiModelProperty(example = "pt-BR")
    private String language;
    @Singular
    private List<StandartError> errors;



}
