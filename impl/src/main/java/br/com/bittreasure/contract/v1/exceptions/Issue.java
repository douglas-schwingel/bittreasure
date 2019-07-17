package br.com.bittreasure.contract.v1.exceptions;

import lombok.Data;

@Data
public class Issue {
    private String id;
    private String message;

    public Issue(Exception e) {
        this.id = e.getClass().toString();
        this.message = e.getMessage();
    }
}
