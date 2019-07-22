package br.com.bittreasure.impl.coin.services;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.bucket.BucketType;
import com.couchbase.client.java.cluster.BucketSettings;
import com.couchbase.client.java.cluster.ClusterManager;
import com.couchbase.client.java.cluster.DefaultBucketSettings;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import org.junit.ClassRule;
import org.junit.Test;
import test.CouchbaseContainer;

import static org.junit.Assert.assertTrue;

public class CoinServiceTest {

    public static CouchbaseContainer couchbase = new CouchbaseContainer("couchbase")
            .withIndex(true)
            .withClusterUsername("admin")
            .withClusterPassword("localhost")
            .withNewBucket(DefaultBucketSettings.builder().enableFlush(true)
                    .name("bittreasure").type(BucketType.COUCHBASE).build())
            .withNewBucket(DefaultBucketSettings.builder().enableFlush(true)
                    .name("exchanges").type(BucketType.COUCHBASE).build());

    static {
        couchbase.start();
    }

    @Test
    public void shouldConnectToCouchbase() {
        CouchbaseEnvironment couchbaseEnvironnement = couchbase.getCouchbaseEnvironnement();

        CouchbaseCluster couchbaseCluster = CouchbaseCluster.create(couchbaseEnvironnement);
        couchbaseCluster.authenticate("admin", "localhost");
        Bucket bittreasure = couchbaseCluster.openBucket("bittreasure");
        Bucket exchanges = couchbaseCluster.openBucket("exchanges");
        ClusterManager cm = couchbaseCluster.clusterManager();
        assertTrue(cm.hasBucket("bittreasure"));
        assertTrue(cm.hasBucket("exchanges"));

    }

}