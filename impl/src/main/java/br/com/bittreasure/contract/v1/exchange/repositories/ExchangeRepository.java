package br.com.bittreasure.contract.v1.exchange.repositories;

import br.com.bittreasure.contract.v1.exchange.models.Exchange;
import org.springframework.data.couchbase.core.query.N1qlSecondaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@N1qlSecondaryIndexed(indexName = "exchangeRepository")
@ViewIndexed(designDoc = "br/com/bittreasure/contract/v1/exchange")
@Repository
public interface ExchangeRepository extends CouchbaseRepository<Exchange, String> {
}
