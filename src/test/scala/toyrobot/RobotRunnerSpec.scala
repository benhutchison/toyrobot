package toyrobot

import java.io._

class RobotRunnerSpec extends org.specs2.mutable.Specification {

  "integration test" ! {
    val commandString =
      """
        |PLACE 3, 3, NORTH
        |MOVE
        |RIGHT
        |MOVE
        |RIGHT
        |MOVE
        |LEFT
        |MOVE
        |REPORT
      """.stripMargin

    val out = new StringWriter()
    new RobotRunner().runFrom(new BufferedReader(new StringReader(commandString)), out)

    out.toString must_== "4,3,EAST\n"
  }

  "unexpected input tolerated" ! {
    val commandString =
      """
        |PLACE 3    ,3, AUST
        |IGNORE
        |PLACE 3    ,3, NORTH
        |MOVE
        |RIGHT
        |MOVE
        |RIGHT
        |
        |MOVE
        |LEFT
        |MOVE
        |REPORT
        |IGNORE
      """.stripMargin

    val out = new StringWriter()
    new RobotRunner().runFrom(new BufferedReader(new StringReader(commandString)), out)

    out.toString must_== "4,3,EAST\n"
  }
}