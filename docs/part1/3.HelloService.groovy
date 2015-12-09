package skillsmatter.demo

import com.amazonaws.services.lambda.runtime.Context

class HelloService {

    Map hello(data, Context context) {
        context.logger.log "received: $data"
        [greeting: "Hello ${data?.name}".toString()]
    }

}
