package toyrobot

import java.io._

object RobotRunner {

  def main(args: Array[String]): Unit = new RobotRunner().runFrom(
    new BufferedReader(new InputStreamReader(System.in)),
    new OutputStreamWriter(System.out)
  )

}

class RobotRunner {
  var robot: Robot = UnplacedRobot

  def runFrom(in: BufferedReader, out: Writer): Unit = {
    Iterator.
      continually(in.readLine()).
      takeWhile(_ != null).
      foreach(interpret(_, out))
  }

  val PlaceCmdRegex = """PLACE *(\d+) *, *(\d+) *, *(\w+)""".r
  val MoveCmdRegex = """MOVE""".r
  val RightCmdRegex = """RIGHT""".r
  val LeftCmdRegex = """LEFT""".r
  val ReportCmdRegex = """REPORT""".r


  def interpret(command: String, out: Writer): Unit = {
    command.trim match {

      case PlaceCmdRegex(xStr, yStr, dirStr) =>
        for {
          x <- parseInt(xStr)
          y <- parseInt(yStr)
          d <- Direction.fromString(dirStr).toRight(s"Unrecognised direction '$dirStr'")
        } {
          robot = robot.place(Table.Table5x5, x, y, d)
        }

      case MoveCmdRegex() =>
        robot = robot.move

      case RightCmdRegex() =>
        robot = robot.right

      case LeftCmdRegex() =>
        robot = robot.left

      case ReportCmdRegex() =>
        robot.report.foreach(s => {out.write(s + "\n"); out.flush()})

      case other =>
        ()//ignore

    }
  }

  private def parseInt(s: String): Either[String, Int] =
    util.Try(s.toInt).toEither.left.map(_ => s"'$s' can't be parsed to Int")

}
