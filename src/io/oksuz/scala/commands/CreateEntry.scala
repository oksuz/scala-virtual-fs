package io.oksuz.scala.commands

import io.oksuz.scala.files.{DirEntry, Directory}
import io.oksuz.scala.filesystem.State

abstract class CreateEntry(name: String) extends Command {

  override def apply(state: State): State = {
    val workingDir = state.workingDirectory

    if (workingDir.hasEntry(name)) {
      state.setMessage(s"Entry $name already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain separators")
    } else if (checkIllegal(name)) {
      state.setMessage(s"$name: illegal entry name")
    } else {
      doCreateEntry(state, name)
    }

  }

  def doCreateEntry(state: State, name: String): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newDir: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newDir)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newDir))
      }
    }


    val workingDir = state.workingDirectory
    val allDirsInPath = workingDir.getAllFoldersInPath
    val newEntry: DirEntry = doSpecificEntry(state)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    val newWorkingDirectory = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWorkingDirectory)
  }

  def checkIllegal(name: String): Boolean =
    name.contains(".")

  def doSpecificEntry(state: State): DirEntry

}
