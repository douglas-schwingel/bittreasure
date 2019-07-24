package br.com.bittreasure.impl.exchange.repositories;

import br.com.bittreasure.impl.exchange.models.Exchange;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@N1qlSecondaryIndexed(indexName = "exchangeRepository")
@ViewIndexed(designDoc = "exchange")
@Repository
public interface ExchangeRepository extends CouchbaseRepository<Exchange, String> {
}
