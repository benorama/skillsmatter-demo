class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        // API
        "/api/quizzes/$quizId/insights"(controller: 'quizInsight') {
            action = [GET: 'index']
        }
        "/api/quizzes/$quizId/votes"(controller: 'quizVote') {
            action = [POST: 'create']
        }
        "/api/votes/$id"(controller: 'quizVote') {
            action = [GET: 'show']
        }

        "/"(view:"/index")
        "/admin"(view: "/admin")  // map the root URL
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
