package io.oksuz.scala.commands
import io.oksuz.scala.files.DirEntry
import io.oksuz.scala.filesystem.State

class Ls extends Command {

  def niceOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head

      s"${entry.getType} ${entry.name}\n${niceOutput(contents.tail)} "
    }
  }

  override def apply(state: State): State = {
    val contents = state.workingDirectory.contents
    state.setMessage(niceOutput(contents))
  }

}
