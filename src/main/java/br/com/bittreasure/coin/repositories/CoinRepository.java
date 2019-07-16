package br.com.bittreasure.coin.repositories;

import br.com.bittreasure.coin.models.Coin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String> {

}
