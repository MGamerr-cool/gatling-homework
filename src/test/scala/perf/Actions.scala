package perf

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import perf.Feeders._

object Actions {

  //------------------- Open -------------------
  val goToWebtours = http("goToWebtoursTest")
    .get("/webtours/")
    .check(status is 200)

  val goToWebtours_Welcome = http("goToWebtours_Welcome")
    .get("/cgi-bin/welcome.pl")
    .queryParam("signOff","true")
    .check(status is 200)

  val goToWebtours_Nav = http("goToWebtours_Nav")
    .get("/cgi-bin/nav.pl")
    .queryParam("in","home")
    .check(status is 200)
    .check(regex("""name="userSession" value="(.+)"""").saveAs("userSession"))

  //------------------- Login -------------------
  val loginPost = http("loginPost")
    .post("/cgi-bin/login.pl")
    .formParam("userSession","${userSession}")
    .formParam("username","${login}")
    .formParam("password","${password}")
    .formParam("login.x","69")
    .formParam("login.y","4")
    .formParam("JSFormSubmit","off")
    .check(status is 200)

  val login_Nav = http("login_Nav")
    .get("/cgi-bin/nav.pl")
    .queryParam("page","menu")
    .queryParam("in","home")
    .check(status is 200)

  val loginGet = http("loginGet")
    .get("/cgi-bin/login.pl")
    .queryParam("intro","true")
    .check(status is 200)

  //------------------- Go to Flight -------------------
  val goToFlight = http("goToFlight")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page","search")
    .check(status is 200)

  val goToFlight_Nav = http("goToFlight_Nav")
    .get("/cgi-bin/nav.pl")
    .queryParam("page","menu")
    .queryParam("in","flights")
    .check(status is 200)

  val goToFlight_Reservations = http("goToFlight_Reservations")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(status is 200)
    .check(regex("""name="departDate" value="(.+)" size=""").saveAs("departDate"))
    .check(regex("""name="returnDate" value="(.+)" size=""").saveAs("returnDate"))

  //------------------- Buy Ticket -------------------
  val BuyTicketStep1 = http("BuyTicketStep1")
    .post("cgi-bin/reservations.pl")
    .formParam("advanceDiscount","0")
    .formParam("depart","${city_depart}")
    .formParam("departDate","${departDate}")
    .formParam("arrive","${city_arrive}")
    .formParam("returnDate","${returnDate}")
    .formParam("numPassengers","1")
    .formParam("seatPref","None")
    .formParam("seatType","Coach")
    .formParam("findFlights.x","66")
    .formParam("findFlights.y","11")
    .formParam(".cgifields","roundtrip")
    .formParam(".cgifields","seatType")
    .formParam(".cgifields","seatPref")
    .check(status is 200)
    .check(regex("""value="([0-9;]+[\/]+[0-9]+[\/]+[0-9]+)""").findAll.saveAs("outboundFlight"))


  val BuyTicketStep2 = http("BuyTicketStep2")
    .post("cgi-bin/reservations.pl")
    .formParam("outboundFlight","${outboundFlight.random()}")
    .formParam("numPassengers","1")
    .formParam("advanceDiscount","0")
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("reserveFlights.x","57")
    .formParam("reserveFlights.y","8")
    .check(status is 200)

  val BuyTicketStep3 = http("BuyTicketStep3")
    .post("cgi-bin/reservations.pl")
    .formParam("firstName","${password}")
    .formParam("lastName","${login}")
    .formParam("address1","street")
    .formParam("address2","city")
    .formParam("pass1","${password} ${login}")
    .formParam("creditCard","")
    .formParam("expDate","")
    .formParam("oldCCOption","")
    .formParam("numPassengers","1")
    .formParam("seatType","Coach")
    .formParam("seatPref","None")
    .formParam("outboundFlight","${outboundFlight.random()}")
    .formParam("advanceDiscount","0")
    .formParam("returnFlight","")
    .formParam("JSFormSubmit","off")
    .formParam("buyFlights.x","64")
    .formParam("buyFlights.y","4")
    .formParam(".cgifields","saveCC")
    .check(status is 200)

  //------------------- Go to Home -------------------
  val goToHome = http("goToHome")
    .get("/cgi-bin/welcome.pl")
    .queryParam("page","menus")
    .check(status is 200)
}
