package skillsmatter.demo

import com.amazonaws.services.lambda.runtime.Context

class HelloService {

    Map hello(data, Context context) {
        context.logger.log "received in groovy: $data"
        [greeting: "Hello ${data?.firstName} ${data?.lastName}".toString()]
    }

    Map ping(data, Context context) {
        context.logger.log "received in groovy: $data"
        data
    }


}