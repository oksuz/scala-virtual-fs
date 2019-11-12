package io.oksuz.scala.commands
import io.oksuz.scala.files.{DirEntry, File}
import io.oksuz.scala.filesystem.State

class Touch(name: String) extends CreateEntry(name) {

  override def doSpecificEntry(state: State): DirEntry =
    File.empty(state.workingDirectory.path, name)
}
