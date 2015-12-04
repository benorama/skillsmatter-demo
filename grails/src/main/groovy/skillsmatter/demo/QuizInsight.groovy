package skillsmatter.demo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import groovy.transform.ToString

@ToString
@DynamoDBTable(tableName="SkillsmatterQuizInsight")
public class QuizInsight {

    @DynamoDBHashKey
    String quizId
    @DynamoDBRangeKey
    String answerId
    @DynamoDBAttribute
    Integer voteCount = 0

}
