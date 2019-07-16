package br.com.bittreasure.v1.coin.repositories;

import br.com.bittreasure.v1.coin.models.Coin;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@N1qlSecondaryIndexed(indexName = "coinRepository")
@ViewIndexed(designDoc = "coin")
@Repository
public interface CoinRepository extends CouchbaseRepository<Coin, String> {

}
