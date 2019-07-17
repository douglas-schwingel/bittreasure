package br.com.bittreasure.contract.v1.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class StandartError {

    private String name;
    private String message;
    private Integer status;
    @Singular
    private List<Issue> issues;
    private String suggestedApplicationAction;
    private String suggestedUserAction;

}
