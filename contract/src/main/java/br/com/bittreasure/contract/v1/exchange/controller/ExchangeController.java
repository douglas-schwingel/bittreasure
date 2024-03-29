package br.com.bittreasure.contract.v1.exchange.controller;

import br.com.bittreasure.contract.v1.exchange.controller.facade.ExchangeControllerFacade;
import br.com.bittreasure.contract.v1.exchange.models.response.ExchangeResponse;
import br.com.bittreasure.contract.v1.exchange.models.response.ListExchangeResponse;
import br.com.bittreasure.impl.exceptions.errors.ResponseError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"v1", "Exchange"})
@RequestMapping("/v1/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final ExchangeControllerFacade facade;

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Exchange load", notes = "Get all exchanges from Coinpaprika and save them")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchanges saved", response = ListExchangeResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
            @ApiResponse(code = 405, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coin not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @PostMapping("/saveAll")
    public ListExchangeResponse saveAll() {
        return facade.saveAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get exchange", notes = "Get exchange based on Its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchange retrivied", response = ExchangeResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
            @ApiResponse(code = 405, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coin not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @GetMapping("/{id}")
    public ExchangeResponse find(@PathVariable("id") String id) {
        return facade.find(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all exchanges", notes = "Get all exchanges")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exchanges retrivied", response = ListExchangeResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
            @ApiResponse(code = 405, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coin not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @GetMapping("/")
    public ListExchangeResponse findAll() {
        return facade.findAll();
    }

}
