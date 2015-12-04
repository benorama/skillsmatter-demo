package skillsmatter.demo

import groovy.transform.ToString

@ToString
class ApiGatewayRequest {

    Map context
    Map headers
    Map params
    Map data

}
