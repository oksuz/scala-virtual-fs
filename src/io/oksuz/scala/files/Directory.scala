package io.oksuz.scala.files

import scala.annotation.tailrec

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {

  def replaceEntry(name: String, newEntry: Directory): Directory =
    new Directory(parentPath, name, contents.filter(e => !e.name.equals(name)) :+ newEntry)

  def findEntry(entryName: String): DirEntry = {

    @tailrec
    def findEntryHelper(name: String, contentList: List[DirEntry]): DirEntry = {
      if (contentList.isEmpty) null
      else if (contentList.head.name.equals(name)) contentList.head
      else findEntryHelper(name, contentList.tail)
    }

    findEntryHelper(entryName, contents)

  }

  def addEntry(newDir: DirEntry): Directory = new Directory(parentPath, name, contents :+ newDir)

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def hasEntry(name: String): Boolean = findEntry(name) != null

  def getAllFoldersInPath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList.filter(e => !e.isEmpty)
    // /a/b/c/ => [a,b,c]
  override def asDirectory: Directory = this

  override def asFile: File = throw new Error

  override def getType: String = "[DIR]"
}

object Directory {

  val SEPARATOR = "/"
  val ROOT_PATH = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())

}