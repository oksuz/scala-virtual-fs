package io.oksuz.scala.files

class File(override val parentPath: String, override val name: String, val contents: String)
  extends DirEntry(parentPath, name) {

  override def asFile: File = this

  override def asDirectory: Directory = throw new Error

  override def getType: String = "[FILE]"
}


object File {

  def empty(parentPath: String, name: String): File = new File(parentPath, name, "")

}