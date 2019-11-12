package io.oksuz.scala.commands
import io.oksuz.scala.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State =
    state.setMessage(state.workingDirectory.path)
}
