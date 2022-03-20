package perf

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder

object Feeders {

  val users: BatchableFeederBuilder[String] = csv("users.csv").random.eager
  val cities_depart: BatchableFeederBuilder[String] = csv("cities.csv").random.eager
  val cities_arrive: BatchableFeederBuilder[String] = csv("cities2.csv").random.eager

}
