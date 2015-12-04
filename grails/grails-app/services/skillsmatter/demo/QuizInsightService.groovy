package skillsmatter.demo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage
import com.amazonaws.services.dynamodbv2.model.*

class QuizInsightService extends AbstractDBService {

    static String TABLE_NAME = 'SkillsmatterQuizInsight'

    /**
     *
     * @param quizId
     * @param answerId
     * @param count, value to increment
     * @return
     */
    def increment(String quizId, String answerId, Integer count = 1) {
        String attributeName = 'voteCount'
        UpdateItemRequest request = new UpdateItemRequest(
                tableName: TABLE_NAME,
                key: [
                        quizId: new AttributeValue().withS(quizId),
                        answerId: new AttributeValue().withS(answerId)
                ],
                returnValues: ReturnValue.UPDATED_NEW
        ).addAttributeUpdatesEntry(
                attributeName,
                new AttributeValueUpdate(
                        action: AttributeAction.ADD,
                        value: new AttributeValue().withN(count.toString())
                )
        )
        UpdateItemResult result = client.updateItem(request)
        result?.attributes[attributeName]?.getN()?.toInteger()
    }

    /**
     *
     * @param quizId
     * @param settings
     * @return
     */
    List<QuizInsight> query(String quizId) {
        DynamoDBQueryExpression query = new DynamoDBQueryExpression()
                .withHashKeyValues(new QuizInsight(quizId: quizId))
        QueryResultPage resultPage = mapper.queryPage(QuizInsight, query)
        resultPage?.results ?: []
    }

}
