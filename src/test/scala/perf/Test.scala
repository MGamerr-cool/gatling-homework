package perf

import io.gatling.http.Predef._
import io.gatling.core.Predef._


class Test extends Simulation {

  val httpProtocol = http
    .baseUrl("https://test.k6.io")
    .acceptHeader("test/html,application/xhtml+xml,appplication/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .disableFollowRedirect

  setUp(
    CommonScenario().inject(atOnceUsers(1)
    )
  ).protocols(httpProtocol)
}
