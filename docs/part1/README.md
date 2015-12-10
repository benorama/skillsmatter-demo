# Part 1

## AWS Lambda

0. Reset **build.gradle** and delete groovy src except **APIGatewayRequest.groovy**
1. Add groovy support to **build.gradle**
2. Create **skillsmatter.demo.HelloService.groovy** and **hello()** function
```groovy
Map hello(data, Context context) {
context.logger.log "Received $data"
[greeting: "Hello ${data?.name}".toString()]
}
```
3. Build deployment package: `gradle build`
4. Create **HelloService** Lambda function
- function name: **HelloService**
- runtime: **Java**
- handler: **skillsmatter.demo.HelloService::hello**
- memory: **128MB**
5. **Test** HelloService
```json
{
"name": "Benoit"
}
```

**Optional**

6. Update **build.gradle** to demo Gradle AWS Plugin `gradle deploy` and `gradle invoke` tasks