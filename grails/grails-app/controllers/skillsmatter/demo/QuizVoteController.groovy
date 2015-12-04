package skillsmatter.demo

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

class QuizVoteController {

    static responseFormats = ['json']

    QuizVoteService quizVoteService

    def create() {
        def data = request.JSON
        if (!data.userName || !data.quizId || !data.answerId) {
            render status: BAD_REQUEST
        }
        respond quizVoteService.create(data.answerId, data.quizId, data.userName)
    }

    def show() {
        respond quizVoteService.load(params.id) ?: [status: NOT_FOUND]
    }

}
