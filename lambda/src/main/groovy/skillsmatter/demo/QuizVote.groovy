package skillsmatter.demo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import groovy.transform.ToString

@ToString
@DynamoDBTable(tableName="SkillsmatterQuizVote")
public class QuizVote {

    @DynamoDBHashKey // Ex.: "${vote.quizId}_${vote.createdByUserName}_${vote.creationDate.time}"
    String id
    @DynamoDBAttribute
    String answerId
    @DynamoDBAttribute
    Date creationDate
    @DynamoDBAttribute
    String quizId
    @DynamoDBAttribute
    String userName

}
