package br.com.bittreasure.impl.coin.filters;

import br.com.bittreasure.impl.coin.filters.models.FilterByRankGreater;
import br.com.bittreasure.impl.coin.filters.models.FilterByRankLess;
import br.com.bittreasure.impl.coin.filters.models.FilterType;
import br.com.bittreasure.impl.coin.filters.models.NoFilter;

public enum ValidCoinFilters {
    NO_FILTER(new NoFilter()),
    RANK_LESS(new FilterByRankLess()),
    RANK_GREATER(new FilterByRankGreater()),
    MARKETCAP(null);

    private FilterType filterType;

    ValidCoinFilters(FilterType filterType) {
        this.filterType = filterType;
    }

    public FilterType getFilterType() {
        return filterType;
    }

}
