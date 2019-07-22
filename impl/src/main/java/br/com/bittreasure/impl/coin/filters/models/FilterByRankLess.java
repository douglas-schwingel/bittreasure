package br.com.bittreasure.impl.coin.filters.models;

import br.com.bittreasure.impl.coin.models.Coin;
import br.com.bittreasure.impl.coin.repositories.CoinRepository;
import br.com.bittreasure.impl.exceptions.ApiException;
import br.com.bittreasure.impl.exceptions.errors.StandartError;
import br.com.bittreasure.impl.exceptions.issues.Issue;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class FilterByRankLess implements FilterType {

    @Override
    public List<Coin> doSearch(CoinRepository repository, String value) {
        Integer rank = 0;
        try {
            rank = Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ApiException(StandartError.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .name(HttpStatus.BAD_REQUEST.name())
                    .message("Invalid value for filter rank_less")
                    .issue(new Issue(e))
                    .suggestedApplicationAction("Make verifications before sending the request")
                    .suggestedUserAction("Filter 'rank_less' needs a valid number. Verify and try again")
                    .build());
        }
        List<Coin> list = new ArrayList<>();
        List<Coin> coins = repository.findAllByRankLessThanOrderByRank(rank);
        coins.forEach(c -> {
            if (c.getRank() > 0) list.add(c);
        });
        return list;
    }
}
