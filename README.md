# lv-cosmosdblogger-lib

Log string payload to Cosmos DB. For Example XML or JSON.

Import JAR-file as dependency

Example code
```
PayloadLog log = new PayloadLog("App navn", "Min status");
CosmosLogger l = new CosmosLogger("https://em-integration-common-db.documents.azure.com:443", "{KEY}", "EM-Integration-Logs", "EM-Integration-Java-Logs");
System.out.println(l.Log(log));
```
