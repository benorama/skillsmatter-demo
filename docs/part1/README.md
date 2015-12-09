==========
AWS Lambda
==========

0. Reset _build.gradle_ and delete groovy src except *APIGatewayRequest.groovy*

1. Add groovy support to _build.gradle_

2. Create *skillsmatter.demo.HelloService.groovy* and *hello()* function

3. Build deployment package: `gradle build`

4. Create HelloService Lambda
	- function name: *HelloService*
	- runtime: *Java*
	- handler: *skillsmatter.demo.HelloService::hello*
	- memory: *128MB*

5. Test HelloService

Optional

6. Update _build.gradle_ to show Gradle AWS Plugin