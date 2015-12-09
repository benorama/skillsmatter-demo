package skillsmatter.demo

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.RegionUtils
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException

abstract class AbstractDBService {

    protected AmazonDynamoDBClient client
    protected DynamoDBMapper mapper

    AbstractDBService() {
        client = new AmazonDynamoDBClient(new DefaultAWSCredentialsProviderChain())
        client.setRegion(RegionUtils.getRegion('us-west-2'))
        mapper = new DynamoDBMapper(client)
    }

    /**
     *
     * @param classToCreate
     * @param readCapacityUnits
     * @param writeCapacityUnits
     */
    void createTable(Class classToCreate,
                     Long readCapacityUnits = 10,
                     Long writeCapacityUnits = 5) {
        DynamoDBTable table = classToCreate.getAnnotation(DynamoDBTable.class)

        try {
            // Check if the table exists
            client.describeTable(table.tableName())
        } catch (ResourceNotFoundException e) {
            CreateTableRequest createTableRequest = mapper.generateCreateTableRequest(classToCreate) // new CreateTableRequest().withTableName(table.tableName())

            // ProvisionedThroughput
            ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput()
                    .withReadCapacityUnits(readCapacityUnits)
                    .withWriteCapacityUnits(writeCapacityUnits)
            createTableRequest.setProvisionedThroughput(provisionedThroughput)

            log.info("Creating DynamoDB table: ${createTableRequest}")

            client.createTable(createTableRequest)
        }
    }

}
