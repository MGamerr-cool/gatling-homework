package perf

import io.gatling.core.Predef._

class Debug extends Simulation{
  setUp(CommonScenario().inject(atOnceUsers(1)))
    .protocols(httpProtocol)
    .assertions(global.responseTime.max.lt(2000))
    .maxDuration(2000)

}
