package se.agilesearch.helloworld.aws.lambda.dynamodb;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import se.agilesearch.helloworld.aws.lambda.dynamodb.domain.PersonRequest;
import se.agilesearch.helloworld.aws.lambda.dynamodb.domain.PersonResponse;

public class AwsLambdaDynamodbDemo implements RequestHandler<PersonRequest, PersonResponse> {

    private String DYNAMODB_TABLE_NAME = "Person";
    // private Regions REGION = Regions.US_EAST_1;


    @Override
    public PersonResponse handleRequest(PersonRequest personRequest, Context context) {

        final AmazonDynamoDB client = AmazonDynamoDBClientBuilder
                .standard()
                .build();
        final var dynamoDB = new DynamoDB(client);

        final var table = dynamoDB.getTable(DYNAMODB_TABLE_NAME);

        final var item = new Item()
                .withPrimaryKey("Id", 1)
                .withString("firstname", "Bahram")
                .withString("lastname", "Jahanshahi");

        final PutItemOutcome putItemOutcome = table.putItem(item);

        return new PersonResponse(putItemOutcome.getPutItemResult().toString());
    }
}
