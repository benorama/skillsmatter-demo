# Part 2

## AWS Lambda

0. Copy groovy source from Grails to Lambda project
- `cp -r ../grails-app/services/ ./src/main/groovy/`
- `cp -r ../src/main/groovy/ ./src/main/groovy/`
1. If required, update region **AbstractDBService.groovy**
`client.setRegion(RegionUtils.getRegion('us-west-2'))`
2. Create **QuizVoteAPIController.groovy**
3. Build deployment package: `gradle build`
4. Create **QuizVoteAPIController** Lambda function
- function name: **QuizVoteAPIService**
- runtime: **Java**
- handler: **skillsmatter.demo.QuizVoteAPIService::handleRequest**
- role: **lambda_dynamo**
- memory: **512MB**
5. **Test** Lambda function

## Amazon API Gateway

6. Create resource **/quizzes/{quizId}/votes**
7. Add **POST** method -> **QuizVoteAPIController** Lambda
8. Edit integration request and copy/paste **mapping template**
9. **Test** method

**Optional start**

10. Create resource **/quizzes/{quizId}/votes/{voteId}**
11. Add **GET** method -> **QuizVoteAPIController** Lambda
12. Edit integration request and copy/paste **mapping template**
13. **Test** method

**Optional end**

14. **Enable CORS**
15. **Deploy** API

## AWS Lambda

16. Create **QuizVoteDBEventProcessor.groovy**
17. Build deployment package: `gradle build`
18. Create **QuizVoteDBEventProcessor** Lambda function
- function name: **QuizVoteVoteDBEventProcessor**
- runtime: **Java**
- handler: **skillsmatter.demo.QuizVoteDBEventProcessor::handleRequest**
- role: **lambda_dynamo_streams**
- memory: *512MB*
19. **Add DynamoDB event** source on SkillsmatterQuizVote table
20. **Test** Lambda function

## Amazon S3 + CloudFront

21. Edit `apiBase` in **app.ts** to point to deployed API
22. **Test** angular app locally
23. **Upload** assets to S3: `gulp publish`
24. **Test** angular app from S3:
http://skillsmatter-demo.s3-website-eu-west-1.amazonaws.com