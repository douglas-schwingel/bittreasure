package br.com.bittreasure.contract.v1.error.controller;

import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public void error(HttpServletRequest request) {
        throw new ApiException(StandartError.builder()
                .message("Invalid uri")
                .status(HttpStatus.NOT_FOUND.value())
                .name(HttpStatus.NOT_FOUND.name())
                .issue(new Issue(new IllegalArgumentException("No endpoint " +
                        request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString() + " mapped.")))
                .suggestedUserAction("Please, verify the uri and try again")
                .suggestedApplicationAction("Punch the goddamn user")
                .build()
        );
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
