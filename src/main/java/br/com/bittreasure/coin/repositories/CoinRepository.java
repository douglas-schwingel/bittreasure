package br.com.bittreasure.coin.repositories;

import br.com.bittreasure.coin.models.Coin;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@N1qlSecondaryIndexed(indexName = "coinRepository")
@ViewIndexed(designDoc = "coin")
@Repository
public interface CoinRepository extends CouchbaseRepository<Coin, String> {

}
