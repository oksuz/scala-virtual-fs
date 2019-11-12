package io.oksuz.scala.filesystem

import java.util.Scanner

import io.oksuz.scala.commands.Command
import io.oksuz.scala.files.Directory

object Filesystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}
