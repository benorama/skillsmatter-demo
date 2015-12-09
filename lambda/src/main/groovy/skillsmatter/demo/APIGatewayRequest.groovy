package skillsmatter.demo

import groovy.transform.ToString

/**
 * Typed request from API Gateway, to be used with this mapping template

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
 */
@ToString
class APIGatewayRequest {

    Map context // Gateway context (HTTP method, request id, identify, etc)
    Map headers	// HTTP headers
    Map params	// Path + query string parameters
    Map data	// Json data (POST only)

}
