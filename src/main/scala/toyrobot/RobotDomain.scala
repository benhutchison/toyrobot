package toyrobot

sealed trait Robot {

  def place(table: Table, x: Int, y: Int, direction: Direction): Robot

  def move: Robot

  def left: Robot

  def right: Robot

  def report: Option[String]

}

case object UnplacedRobot extends Robot {

  def place(table: Table, x: Int, y: Int, direction: Direction): Robot =
    if (table.contains(x, y))
      PlacedRobot(x, y, direction, table)
    else
      this

  def move: Robot = this

  def left: Robot = this

  def right: Robot = this

  def report = None
}

case class PlacedRobot private (x: Int, y: Int, direction: Direction, table: Table) extends Robot {

  def place(table: Table, x: Int, y: Int, direction: Direction) = this

  def move: Robot = {
    val x1 = x + direction.dx
    val y1 = y + direction.dy
    if (table.contains(x1, y1))
      copy(x = x1, y = y1)
    else
      this
  }

  def left: Robot = copy(direction = this.direction.left)

  def right: Robot = copy(direction = this.direction.right)

  def report = Some(s"$x,$y,$direction")
}

case class Direction private (value: Int, name: String, dx: Int, dy: Int) {

  override def toString = name

  def left: Direction = rotate(-1)

  def right: Direction = rotate(1)

  private def rotate(r: Int) = Direction.Values((value + r + Direction.Count) % Direction.Count)

}
object Direction {

  val North = Direction(0, "NORTH", 0, 1)
  val East = Direction(1, "EAST", 1, 0)
  val South = Direction(2, "SOUTH", 0, -1)
  val West = Direction(3, "WEST", -1, 0)
  def Count = Values.size

  def fromString(name: String): Option[Direction] = Values.values.find(_.name.equalsIgnoreCase(name))

  private val Values = Map(
    0 -> North,
    1 -> East,
    2 -> South,
    3 -> West,
  )

}

case class Table(width: Int, height: Int) {

  def contains(x: Int, y: Int) = x >= 0 && x < width && y >= 0 && y < height
}
object Table {

  val Table5x5 = Table(5, 5)

}

