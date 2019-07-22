package br.com.bittreasure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BitTreasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitTreasureApplication.class, args);
	}

}
