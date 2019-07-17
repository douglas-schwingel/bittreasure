package br.com.bittreasure.contract.v1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {

    private String namespace;
    private String language;
    private List<StandartError> errors;



}
