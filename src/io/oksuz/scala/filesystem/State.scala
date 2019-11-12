package io.oksuz.scala.filesystem

import io.oksuz.scala.files.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String) {

  def show: Unit =
    print(State.SHELL_TOKEN)
    println(output)


  def setMessage(message: String): State =
    State(root, workingDirectory, message)

}


object State {

  val SHELL_TOKEN = "$ "

  def apply(root: Directory, workingDirectory: Directory, output: String = ""): State =
    new State(root, workingDirectory, output)

}