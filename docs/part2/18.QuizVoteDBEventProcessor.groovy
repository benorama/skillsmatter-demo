package skillsmatter.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.DynamodbEvent

class QuizVoteDBEventProcessor implements RequestHandler<DynamodbEvent, String> {

    private QuizInsightService quizInsightService

    QuizVoteDBEventProcessor() {
        quizInsightService = new QuizInsightService()
    }

    /**
     *
     * @param event
     * @param context
     * @return
     */
    String handleRequest(DynamodbEvent event, Context context) {
        context.logger.log "event:$event"
        try {
            Map voteByQuizIdAndAnswerIds = [:]
            event.records.each { DynamodbEvent.DynamodbStreamRecord record ->
                if (record.dynamodb?.newImage?.answerId && record.dynamodb?.newImage?.quizId) {
                    String answerId = record.dynamodb.newImage.answerId.getS() // Get string from DynamoDB AttributeValue
                    String quizId = record.dynamodb.newImage.quizId.getS() // Get string from DynamoDB AttributeValue
                    if (!voteByQuizIdAndAnswerIds[quizId]) {
                        voteByQuizIdAndAnswerIds[quizId] = [:]
                    }
                    if (!voteByQuizIdAndAnswerIds[quizId][answerId]) {
                        voteByQuizIdAndAnswerIds[quizId][answerId] = 1
                    } else {
                        voteByQuizIdAndAnswerIds[quizId][answerId]++
                    }
                }
            }
            voteByQuizIdAndAnswerIds.each { String quizId, Map voteByAnswerIds ->
                voteByAnswerIds.each { String answerId, Integer voteCount ->
                    quizInsightService.increment(quizId, answerId, voteCount)
                }
            }
            "Successfully processed ${event.records.size()} records."
        } catch (exception) {
            context.logger.log "An error occured with event:$event - exception:$exception"
        }
    }

}
