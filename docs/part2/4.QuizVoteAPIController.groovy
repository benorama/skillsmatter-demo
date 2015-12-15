package skillsmatter.demo

import com.amazonaws.services.lambda.runtime.Context

class QuizVoteAPIController {

    private QuizVoteService quizVoteService

    QuizVoteAPIController() {
        quizVoteService = new QuizVoteService()
    }

    /**
     *
     * @param request
     * @param context
     * @return
     */
    def handleRequest(APIGatewayRequest request, Context context) {
        context.logger.log "event: $request"
        quizVoteService.create(request.data.answerId, request.params.quizId, request.data.userName)
    }

}
