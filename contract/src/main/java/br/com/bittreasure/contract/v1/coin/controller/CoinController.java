package br.com.bittreasure.contract.v1.coin.controller;

import br.com.bittreasure.contract.v1.coin.controller.facade.CoinControllerFacade;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListCompleteCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.ListSimplifiedCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.SimplifiedCoinResponse;
import br.com.bittreasure.impl.exceptions.errors.ResponseError;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"v1", "Coin"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping("/v1/coins")
@Slf4j
public class CoinController {

    private final CoinControllerFacade coinControllerFacade;

    public CoinController(CoinControllerFacade coinControllerFacade) {
        this.coinControllerFacade = coinControllerFacade;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Simplified Coins", notes = "Get the simplified coin version containing basic informations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coins retrivied", response = SimplifiedCoinResponse.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Coins not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/simplified")
    public SimplifiedCoinResponse getSimplifiedCoins() {
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get Complete Coins", notes = "Get the complete coin version containing all the informations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coins retrivied", response = ListCompleteCoinResponse.class),
            @ApiResponse(code = 403, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coins not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @GetMapping("/complete")
    public ListCompleteCoinResponse getCompleteCoins() {
        return coinControllerFacade.findAllComplete();
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get coin", notes = "Get coin information based on its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coin retrivied", response = CoinResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
            @ApiResponse(code = 403, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coin not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @GetMapping("/{id}")
    public CoinResponse find(@ApiParam(value = "Coin id", example = "btc-bitcon", required = true)
                             @PathVariable("id") String id,

                             @ApiParam(value = "CoinFilter by name", example = "BitCoin")
                             @RequestParam("filter") @Nullable String filter,

                             @ApiParam(value = "Filter value", example = "BitCoin")
                             @RequestParam("value") @Nullable String value) {
        return coinControllerFacade.find(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all coins", notes = "Get all coins. Can be filtered")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coins retrivied", response = ListSimplifiedCoinResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ResponseError.class),
            @ApiResponse(code = 403, message = "Method not allowed", response = ResponseError.class),
            @ApiResponse(code = 404, message = "Coin not found", response = ResponseError.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseError.class)
    })
    @GetMapping("/")
    public ListSimplifiedCoinResponse findAll(@ApiParam(value = "CoinFilter by name", example = "BitCoin")
                             @RequestParam("filter") @Nullable String filter,

                             @ApiParam(value = "Filter value", example = "BitCoin")
                             @RequestParam("value") @Nullable String value) {
        return coinControllerFacade.findAll(filter, value);
    }


}
