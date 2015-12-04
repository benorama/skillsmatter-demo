package skillsmatter.demo

import com.amazonaws.services.lambda.runtime.Context

class QuizInsightAPIController {

    private QuizInsightService quizInsightService

    QuizInsightAPIController() {
        quizInsightService = new QuizInsightService()
    }

    /**
     *
     * @param request
     * @param context
     * @return
     */
    def handleRequest(ApiGatewayRequest request, Context context) {
        context.logger.log "event:$request"
        def response
        try {
            switch (request.context?.httpMethod) {
                case 'GET':
                    response = quizInsightService.query(request.params.quizId.toString())
                    break
                default:
                    return [code: 400, message: "Unsupported operation", event: request]
            }
        } catch (exception) {
            return [code: 500, message: "An error occured", event: request, exception: exception]
        }
        response
    }

}
