package skillsmatter.demo

import groovy.transform.ToString

@ToString
class APIGatewayRequest {

    Map context // Gateway context (HTTP method, request id, identify, etc)
    Map headers	// HTTP headers
    Map params	// Path + query string parameters
    Map data	// Json data (POST only)

}
