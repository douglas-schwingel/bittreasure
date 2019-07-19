package br.com.bittreasure.impl.coin.filters;

import br.com.bittreasure.impl.coin.filters.models.FilterByGreaterRank;
import br.com.bittreasure.impl.coin.filters.models.FilterByLesserRank;
import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.filters.models.NoFilter;

public enum ValidCoinFilters {
    NO_FILTER(new NoFilter()),
    RANK_LESSER(new FilterByLesserRank()),
    RANK_GREATER(new FilterByGreaterRank()),
    MARKETCAP(null);

    private FilterType filterType;

    ValidCoinFilters(FilterType filterType) {
        this.filterType = filterType;
    }

    public FilterType getFilterType() {
        return filterType;
    }
}
