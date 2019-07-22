package br.com.bittreasure.impl.coin.repositories;

import br.com.bittreasure.impl.coin.models.Coin;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@N1qlSecondaryIndexed(indexName = "coinRepository")
@ViewIndexed(designDoc = "coin")
@Repository
public interface CoinRepository extends CouchbaseRepository<Coin, String> {

    List<Coin> findAllByRankLessThanOrderByRank(Integer rank);
    List<Coin> findAllByRankGreaterThanOrderByRank(Integer rank);
}
