package br.com.bittreasure.impl.exceptions.errors;

import br.com.bittreasure.impl.exceptions.issues.Issue;
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
