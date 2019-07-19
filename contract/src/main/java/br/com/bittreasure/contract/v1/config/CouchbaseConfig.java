package br.com.bittreasure.contract.v1.config;

import br.com.bittreasure.impl.exchange.models.Exchange;
import com.couchbase.client.java.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.couchbase.repository.support.IndexManager;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Bean
    public Bucket exchangesBucket() throws Exception {
        return couchbaseCluster().openBucket("exchanges", "123456");
    }

    @Bean
    public CouchbaseTemplate exchangesTemplate() throws Exception {
        CouchbaseTemplate couchbaseTemplate = new CouchbaseTemplate(couchbaseClusterInfo(),
                exchangesBucket(), mappingCouchbaseConverter(), translationService());
        couchbaseTemplate.setDefaultConsistency(getDefaultConsistency());
        return couchbaseTemplate;
    }

    @Override
    protected void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        try {
            mapping
                    .mapEntity(Exchange.class, exchangesTemplate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.configureRepositoryOperationsMapping(mapping);
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("localhost");
    }

    @Override
    protected String getBucketName() {
        return "bittreasure";
    }

    @Override
    protected String getBucketPassword() {
        return "123456";
    }

    @Override
    public IndexManager indexManager() {
        return new IndexManager(true, true, true);
    }
}
