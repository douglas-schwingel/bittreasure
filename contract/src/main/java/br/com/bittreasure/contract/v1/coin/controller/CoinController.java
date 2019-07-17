package br.com.bittreasure.contract.v1.coin.controller;

import br.com.bittreasure.contract.v1.coin.filters.CoinFilter;
import br.com.bittreasure.contract.v1.coin.models.response.CompleteCoinResponse;
import br.com.bittreasure.contract.v1.coin.models.response.SimplifiedCoinResponse;
import br.com.bittreasure.contract.v1.coin.controller.facade.CoinControllerFacade;
import br.com.bittreasure.contract.v1.coin.models.response.CoinResponse;
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
            @ApiResponse(code = 200, message = "Coins retrivied", response = SimplifiedCoinResponse.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Coins not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/complete")
    public CompleteCoinResponse getCompleteCoins() {
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get coin", notes = "Get coin information based on its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Coin retrivied", response = CoinResponse.class),
            @ApiResponse(code = 403, message = "Method not allowed"),
            @ApiResponse(code = 404, message = "Coin not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/{id}")
    public CoinResponse find(@ApiParam(value = "Coin id", example = "btc-bitcon", required = true)
                             @PathVariable("id") String id,

                             @ApiParam(value = "CoinFilter by name", example = "BitCoin")
                             @RequestParam("filter") @Nullable String filter,

                             @ApiParam(value = "CoinFilter by name", example = "BitCoin")
                             @RequestParam("value") @Nullable String value) {
        CoinFilter.verificaSeEhValido(filter);
        return coinControllerFacade.find(id, filter, value);
    }


}
