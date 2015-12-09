==========
AWS Lambda
==========

0. Copy groovy source from grails to lambda project
	- `cp -r grails-app/services/ ../lambda/src/main/groovy/`
	- `cp -r src/main/groovy/ ../lambda/src/main/groovy/`

1. Update region *AbstractDBService.groovy*
	client.setRegion(RegionUtils.getRegion('us-west-2'))

2. Create *QuizVoteAPIController.groovy*

3. Build deployment package: `gradle build`

4. Create *QuizVoteAPIController* Lambda
	- function name: *QuizVoteAPIService*
	- runtime: *Java*
	- handler: *skillsmatter.demo.QuizVoteAPIService::handleRequest*
	- role: *dynamod_lambda*
	- memory: *512MB*

5. Test QuizVoteAPIService

==================
Amazon API Gateway
==================

6. Create resources */quizzes/{quizId}/votes*

7. Create method *POST*
	- type: *Lambda function*
	- region: *us-west-2*

8. Edit integration request and copy/paste mapping template

9. Test POST method execution in AWS console

10. Create resources */quizzes/{quizId}/votes/{voteId}*

11. Edit integration request and copy/paste mapping template

12. Test GET method execution in AWS console

13. Enable CORS

14. Deploy API

==========
AWS Lambda
==========

15. Create *QuizVoteDBEventProcessor.groovy*

16. Build deployment package: `gradle build`

17. Create *QuizVoteDBEventProcessor* Lambda
	- function name: *QuizVoteVoteDBEventProcessor*
	- runtime: *Java*
	- handler: *skillsmatter.demo.QuizVoteDBEventProcessor::handleRequest*
	- role: *dynamod_lambda_streams*
	- memory: *512MB*

18. Test *QuizVoteDBEventProcessor*

19. Add DynamoDB event source on SkillsmatterQuizVote table

20. Test QuizVoteAPIService


===========
Angular App
===========

18. Edit *apiBase* in *app.ts*

19. Test angular app locally

20. Upload assets to S3: `gulp publish`

21. Test angular app from S3:
	http://skillsmatter-demo.s3-website-eu-west-1.amazonaws.com