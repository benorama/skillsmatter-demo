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
        def response
        switch (request.context?.httpMethod) {
            case 'POST':
                if (!request.params.quizId || !request.data.answerId || !request.data.userName ) {
                    // Return 400 error
                    response = [code: 400, message: "Invalid params", event: request]
                } else {
                    // Create vote
                    response = quizVoteService.create(request.data.answerId, request.params.quizId, request.data.userName)
                }
                break
            case 'GET':
                if (request.params.voteId) {
                    // Return vote or 404 error
                    response = quizVoteService.load(request.params.voteId.toString()) ?: [code: 404, message: "Not found"]
                } else {
                    // List votes
                    response = [] // To implement
                }
                break
            default:
                response = [code: 400, message: "Unsupported operation", event: request]
        }
        context.logger.log "response: $response"
        response
    }

}
