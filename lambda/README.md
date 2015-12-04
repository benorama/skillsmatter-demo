See reference here:
http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html#input-variable-reference


```
#set($keys = [])
#foreach($key in $input.params().querystring.keySet())
  #set($success = $keys.add($key))
#end

#foreach($key in $input.params().path.keySet())
  #if(!$keys.contains($key))
    #set($success = $keys.add($key))
  #end
#end

{
   "context" : {
        "httpMethod": "$context.httpMethod",
        "requestId": "$context.requestId",
        "stage": "$context.stage"
   },
   "headers": {
        #foreach($key in $input.params().header.keySet())
          "$key": "$util.escapeJavaScript($input.params($key))"#if($foreach.hasNext),#end
        #end
   },
   "params" : {
        #foreach($key in $keys)
          "$key": "$util.escapeJavaScript($input.params($key))"#if($foreach.hasNext),#end
        #end
   },
   "data": $input.json('$')
}
```

Vote example:
```json
{
	"context": {
		"httpMethod": "POST"
	},
	"headers": {
		"Accept":"*/*", "Accept-Encoding":"gzip"
	},
	"params": {
		"quizId": "1"
	},
	"data": {
		"answerId": "c-3po", 
		"userName": "ben"
	}
}
```

Insert example:

````json
{
  "Records": [
    {
      "eventID": "01c9fa2e2758cf319a6304e953074aa1", 
      "eventName": "INSERT", 
      "eventVersion": "1.0", 
      "eventSource": "aws:dynamodb", 
      "awsRegion": "us-east-1", 
      "dynamodb": {
        "Keys": {"id": {"S": "1_ben_1448201392279"}, 
        "NewImage": {
          "answerId": {"S": "toto"}, 
          "createdByUserName": {"S": "ben"}, 
          "quizId": {"S": "1"}, 
          "id": {"S": "1_ben_1448201392279"}, 
          "creationDate": {"S": "2015-11-22T14:09:52.279Z"}
        }, 
        "SequenceNumber": "8227300000000000898897344", 
        "SizeBytes": 117, 
        "StreamViewType": "NEW_AND_OLD_IMAGES"
      }, 
      "eventSourceARN": "arn:aws:dynamodb:us-east-1:0123465798:table/SkillsmatterVote/stream/2015-11-22T13:40:13.063"
    }
  ]
}
```