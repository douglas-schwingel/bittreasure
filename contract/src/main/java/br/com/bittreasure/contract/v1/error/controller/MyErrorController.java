package br.com.bittreasure.contract.v1.error.controller;

import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ApiIgnore
@RestController
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public void error(HttpServletRequest request) {
        HttpStatus httpStatus = getStatus(request);
        throw new ApiException(StandartError.builder()
                .message(httpStatus.getReasonPhrase())
                .status(httpStatus.value())
                .name(httpStatus.name())
                .issue(new Issue(httpStatus.getDeclaringClass().getName(), httpStatus.getReasonPhrase()))
                .suggestedUserAction("Please, verify the uri and try again")
                .suggestedApplicationAction("Punch the goddamn user")
                .build()
        );
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
