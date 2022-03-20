package perf

import io.gatling.core.Predef._

class MaximumSimulation extends Simulation {

  setUp(
    CommonScenario().inject(
      incrementUsersPerSec(0.4) // интенсивность на ступень
        .times(10) // Количество ступеней
        .eachLevelLasting(120) // Длительность полки/секунду
        .separatedByRampsLasting(10) // Длительность разгона
        .startingFrom(0) // Начало нагрузки с
    )
  ).protocols(httpProtocol)
}
