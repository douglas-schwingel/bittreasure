package br.com.bittreasure.impl.exceptions.errors;

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
