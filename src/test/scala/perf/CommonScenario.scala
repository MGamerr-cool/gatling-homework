package perf

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import perf.Feeders._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().mainScenario
}

class CommonScenario {

  val open = group("open"){
    exec(Actions.goToWebtours)
      .exec(Actions.goToWebtours_Welcome)
      .exec(Actions.goToWebtours_Nav)
  }

  val login = group("login"){
      exec(Actions.loginPost)
      .exec(Actions.login_Nav)
      .exec(Actions.loginGet)
  }

  val goToFlight = group("goToFlight"){
    exec(Actions.goToFlight)
      .exec(Actions.goToFlight_Nav)
      .exec(Actions.goToFlight_Reservations)
  }

  val buyTicket = group("buyTicket"){
    exec(Actions.BuyTicketStep1)
      .exec(Actions.BuyTicketStep2)
      .exec(Actions.BuyTicketStep3)
  }

  val goToHome = group("goToHome"){
    exec(Actions.goToHome)
      .exec(Actions.loginGet)
      .exec(Actions.login_Nav)
  }

  val mainScenario = scenario("mainScenario")
    .exec(open)
    .feed(users)
    .exec(login)
    .exec(goToFlight)
    .feed(cities_depart)
    .feed(cities_arrive)
    .exec(buyTicket)
    .exec(goToHome)

}
