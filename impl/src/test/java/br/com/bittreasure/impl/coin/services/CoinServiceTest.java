package br.com.bittreasure.impl.coin.services;

import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import org.junit.ClassRule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

import static org.junit.Assert.*;

public class CoinServiceTest {

    @ClassRule
    public GenericContainer couchbase = new GenericContainer("dschwingel/mycouchbase:latest")
            .withExposedPorts(8091,8092,8093,8094,11207,11210,11211,18091,18092,18093)
            .waitingFor(new CouchbaseWaitStrategy());

    @Test
    public void shouldConnectToCouchbase() {
        CouchbaseEnvironment env = DefaultCouchbaseEnvironment.builder()
                .bootstrapCarrierDirectPort(couchbase.getMappedPort(11210))
                .bootstrapCarrierSslPort(couchbase.getMappedPort(11207))
                .bootstrapHttpDirectPort(couchbase.getMappedPort(8091))
                .bootstrapHttpSslPort(couchbase.getMappedPort(18091))
                .build();

    }

}