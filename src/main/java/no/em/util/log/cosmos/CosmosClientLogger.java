package no.em.util.log.cosmos;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.SqlParameter;
import com.azure.cosmos.models.SqlQuerySpec;
import com.azure.cosmos.models.ThroughputProperties;
import no.em.util.log.cosmos.models.PayloadLog;

import java.util.UUID;

/**
 * @since 2.0
 */
public class CosmosClientLogger implements Logger {
  private final String dbName;
  private final String collectionName;
  private final CosmosAsyncClient client;

  /**
   * Construct and init Cosmos DB logger.
   *
   * @param hostname       Ex: em-integration-common-db.documents.azure.com:443
   * @param key            CosmosDB key
   * @param dbName         Database name. Ex: EM-Integration-Logs
   * @param collectionName Collection Name Ex: EM-Integration-Java-Logs
   */

  public CosmosClientLogger(String hostname, String key, String dbName, String collectionName) {
    this.dbName = dbName;
    this.collectionName = collectionName;
    client = new CosmosClientBuilder()
            .endpoint(hostname)
            .key(key)
            .consistencyLevel(ConsistencyLevel.SESSION)
            .gatewayMode()
            .buildAsyncClient();
    init();
  }

  private void init() {
    this.client.createDatabaseIfNotExists(dbName)
            .map(response -> this.client.getDatabase(dbName))
            .flatMap(dbClient -> dbClient.createContainerIfNotExists(
                            collectionName,
                            "/workflowName",
                            ThroughputProperties.createManualThroughput(400)
                    )
            )
            .subscribe();
  }

  /**
   * Log to CosmosDB
   *
   * @return 1 if successful, 0 otherwise.
   * @see no.em.util.log.cosmos.models.PayloadLog
   */
  public int log(PayloadLog log) {
    //TODO: Validate log fields

    //Add guid
    log.setId(this.getGUID());
    return getContainer()
            .createItem(log)
            .onErrorResume(error -> null)
            .map(response -> {
              if (response != null) {
                return response.getStatusCode();
              }
              return -1;
            }).block();
  }

  @Override
  public boolean workflowExists(String workflowId) {
    var spec = new SqlQuerySpec("SELECT * FROM c WHERE workflowId = @workflowId", new SqlParameter("workflowId", workflowId));
    return getContainer().queryItems(spec, PayloadLog.class).count().block() > 0;
  }

  private CosmosAsyncContainer getContainer() {
    var database = this.client.getDatabase(dbName);
    return database.getContainer(collectionName);
  }

  public String getGUID() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }

}
