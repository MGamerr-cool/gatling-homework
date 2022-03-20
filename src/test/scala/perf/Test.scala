package perf

import io.gatling.http.Predef._
import io.gatling.core.Predef._


class Test extends Simulation {

  val rampupDuration = 1200; // 20 минут * 60
  val holdDuration = 3600; // 60 минут * 60
  val rampdownDuration = rampupDuration;

  setUp(
    CommonScenario().inject(
      rampUsersPerSec(0) to 0.4 during (rampupDuration),
      constantUsersPerSec(0.4) during (holdDuration),
      rampUsersPerSec(0.4) to 0 during (rampdownDuration)
    )
  ).protocols(httpProtocol)
}
