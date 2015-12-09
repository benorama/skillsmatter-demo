package skillsmatter.demo

import static org.springframework.http.HttpStatus.BAD_REQUEST

class QuizInsightController {

    static responseFormats = ['json']

    QuizInsightService quizInsightService

    def index() {
        if (!params.quizId) {
            render status: BAD_REQUEST
            return
        }
        respond quizInsightService.query(params.quizId)

    }
}
