## Toy Robot

Ben Hutchison

### Specification

. The application is a simulation of a toy robot moving on a square tabletop, of dimensions 5 units x 5 units.

. There are no other obstructions on the table surface.

. The robot is free to roam around the surface of the table, but must be prevented from falling to destruction. Any movement
that would result in the robot falling from the table must be prevented, however further valid movement commands must still
be allowed.

. Create an application that can read in commands of the following form -

PLACE X,Y,F
MOVE
LEFT
RIGHT
REPORT

. PLACE will put the toy robot on the table in position X,Y and facing NORTH, SOUTH, EAST or WEST.

. The origin (0,0) can be considered to be the SOUTH WEST most corner.

. The first valid command to the robot is a PLACE command, after that, any sequence of commands may be issued, in any order, including another PLACE command. The application should discard all commands in the sequence until a valid PLACE command has been executed.

. MOVE will move the toy robot one unit forward in the direction it is currently facing.

. LEFT and RIGHT will rotate the robot 90 degrees in the specified direction without changing the position of the robot.

. REPORT will announce the X,Y and orientation of the robot.

. A robot that is not on the table can choose to ignore the MOVE, LEFT, RIGHT and REPORT commands.

. Provide test data to exercise the application.

Constraints:

The toy robot must not fall off the table during movement. This also includes the initial placement of the toy robot.
Any move that would cause the robot to fall must be ignored.


### Requirements

Java and SBT must be installed on your system ([instructions](https://www.scala-sbt.org/1.0/docs/Setup.html)).

### Running

The program reads input from stdin and reports to stdout. Commands other than those above are ignored.

The end the program from an interactive prompt, send an EOF signal (Ctl-D on Mac OSX)

Example
```
> sbt run
...
[info] Running toyrobot.RobotRunner
PLACE 1,1,EAST
MOVE
RIGHT
MOVE
REPORT
2,0,SOUTH
<ctl-d>
[success] Total time: 34 s, completed 23/08/2018 1:42:54 PM
```

### Unit Tests

```
sbt test
```

### Packaging as a jar

```
sbt package
```
The jar will be in [target/scala-2.12/toyrobot_2.12-0.1.jar](target/scala-2.12/toyrobot_2.12-0.1.jar).

As a scala app, it depends upon the standard library. To run it you can use eg `scala -classpath target/scala-2.12/toyrobot_2.12-0.1.jar`
if you have [Scala installed](https://www.scala-lang.org/download/).