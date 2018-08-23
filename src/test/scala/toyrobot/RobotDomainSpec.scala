package toyrobot

import java.io._

class RobotDomainSpec extends org.specs2.mutable.Specification {
  import Table._
  import Direction._

  eg (UnplacedRobot.place(Table5x5, 1, 1, North).move.report must_== Some("1,2,NORTH"))

  eg (UnplacedRobot.place(Table5x5, 3, 3, West).move.report must_== Some("2,3,WEST"))

  eg (UnplacedRobot.place(Table5x5, 3, 3, West).place(Table5x5, 1, 1, North).report must_== Some("3,3,WEST"))

  eg (UnplacedRobot.report must_== Option.empty)

  eg (UnplacedRobot.place(Table5x5, 1, 1, North).left.move.report must_== Some("0,1,WEST"))

  eg (UnplacedRobot.place(Table5x5, 1, 1, North).right.move.report must_== Some("2,1,EAST"))

  eg(Table5x5.contains(-1, -1) must beFalse)

  eg(Table5x5.contains(0, 0) must beTrue)

  eg(Table5x5.contains(4, 4) must beTrue)

  eg(Table5x5.contains(5, 5) must beFalse)

  "cannot move off the table" ! {
    UnplacedRobot.place(Table5x5, 4, 4, North).move.report must_== Some("4,4,NORTH")
  }

  "6 rotations is a 480 degree rotation" ! {
    UnplacedRobot.place(Table5x5, 1, 1, West).right.right.right.right.right.right.move.report must_== Some("2,1,EAST")
  }

  "unplaced robots ignore move and turn commands" ! {
    UnplacedRobot.left.move.left.right must_== UnplacedRobot
  }

}