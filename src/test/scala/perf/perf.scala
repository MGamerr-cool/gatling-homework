
import io.gatling.core.Predef._
import io.gatling.http.Predef._

package object perf {

  val httpProtocol = http
    //.baseUrl("http://www.load-test.ru:1080/")
    //.baseUrl("http://webtours.load-test.ru:1080/")
    .baseUrl("http://webtours.load-test.ru:1090/")
    .acceptHeader("test/html,application/xhtml+xml,appplication/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .disableFollowRedirect
}
