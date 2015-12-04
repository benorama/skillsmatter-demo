import grails.rest.render.json.JsonRenderer
import skillsmatter.demo.QuizVote

// Place your Spring DSL code here
beans = {
    bookRenderer(JsonRenderer, QuizVote) {
        excludes = ['class']
    }
    corsFilter(CorsFilter)
}
