package skillsmatter.demo

class QuizVoteService extends AbstractDBService {

    /**
     *
     * @param id
     * @return
     */
    QuizVote load(String id) {
        mapper.load(QuizVote, id)
    }

    /**
     *
     * @param answerId
     * @param quizId
     * @param userName
     * @return
     */
    QuizVote create(String answerId, String quizId, String userName) {
        Date creationDate = new Date()
        QuizVote vote = new QuizVote(
                id: "${quizId}_${userName}_${creationDate.time}", // Build unique hash key, optimized for sharding
                answerId: answerId,
                creationDate: creationDate,
                quizId: quizId,
                userName: userName
        )
        mapper.save(vote)
        vote
    }

}
