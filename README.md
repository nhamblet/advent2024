# advent2024
My work on the 2024 edition of adventofcode.com puzzles

# Workstation Setup

Trying VSCode on a Windows laptop again, because it's sorta what I've got.

Installed a few plugins, not sure which are right / needed:
* Java
* Maven for Java
* Sbt (then disabled based on [docs](https://scalameta.org/metals/docs/editors/vscode/))
* Scala (Metals)
* Scala Syntax (official)

Made `day01` directory and a `Day01Part1.scala` as a "Hello World". Wasn't sure what to do
next, having only used Maven before, not sbt. But then
[this page](https://www.reddit.com/r/scala/comments/jp4c9q/creating_local_scala_projects_in_vs_codemetals/?rdt=46723)
suggested an empty `built.sbt` might work, so I added that, and behold! The Metals plugin did... something...
and I imported the project, and now have more files and folders to play in! Moved my source file to `src/main/scala`
and then changed my `class ... extends App` to `object`, and got the little `Run` button, and it worked!

Debug button didn't stop on breakpoints... changed things around a bit and seems to have made it happier:
1. Moved build.sbt to project root. Was going to have separate builds for each day, but whatever
2. Moved the class to a package, which works for separating the days anyway
Then debug worked!

To add tests, add the `src/test/scala/day01` directory. Follow https://www.scalatest.org/user_guide to add
dependencies to `build.sbt` and make a first test. Can now run and debug tests with handy buttons in the editor. Nice.
