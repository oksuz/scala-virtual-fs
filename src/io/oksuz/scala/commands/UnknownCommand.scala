package io.oksuz.scala.commands
import io.oksuz.scala.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State =
    state.setMessage("Command not found")

}
