Skillsmatter demo app
=====================

This is the demo app for my [Skillsmatter Groovy & Grails eXchange 2015](https://skillsmatter.com/conferences/6863-groovy-grails-exchange-2015) in London: **Serverless microservice using AWS Lambda and Groovy**.

# Slides

GGX presentation can be found here:
[https://speakerdeck.com/benorama/serverless-microservices-using-aws-lambda-and-groovy]:(https://speakerdeck.com/benorama/serverless-microservices-using-aws-lambda-and-groovy)

# Technology used

Front:
- _Typescript_ (1.6.2)
- _Angular2_ (alpha36)
- _Bootstrap4_ (alpha)

Back:
- _Groovy_ (2.4.4)
- _Grails_ (3.1.0.M3)
- _AWS Lambda_
- _AWS API Gateway_
- _AWS DynamoDB_

# Running the app

To run the front end (Angular app), execute:
- `bower install`
- `npm install`
- `gulp`

To run the back end (Grails app), execute:
- `grails run-app`

Note: you must have configured your AWS credentials locally (with DynamoDB permissions).
You can create `SkillsmatterQuizInsight` and `SkillsmatterQuizVote` tables automatically by running the following script at `http://localhost:8080/console`.

```groovy
import skillsmatter.demo.*

ctx.quizInsightService.createTable(QuizInsight)
ctx.quizVoteService.createTable(QuizVote)
```
