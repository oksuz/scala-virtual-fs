package io.oksuz.scala.commands
import io.oksuz.scala.files.{DirEntry, Directory}
import io.oksuz.scala.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {

  override def doSpecificEntry(state: State): DirEntry =
    Directory.empty(state.workingDirectory.path, name)
}
