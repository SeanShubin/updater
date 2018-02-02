package com.seanshubin.updater.domain

import java.nio.charset.Charset
import java.nio.file.Path

import com.seanshubin.updater.xml.Structure.Node

class PomFileServiceImpl(fileFinder: FileFinder, files: FilesContract, charset: Charset) extends PomFileService {
  override def loadAllUnderPath(path: Path): Seq[PomFile] = {
    val files = fileFinder.findByName(path, "pom.xml")
    val pomFiles = files.map(load)
    pomFiles
  }

  override def store(pomFile: PomFile): Unit = ???

  private def load(file: Path): PomFile = {
    val inputStream = files.newInputStream(file)
    val node = Node.fromInputStream(inputStream)
    val pomFile = new PomFile(node)
    pomFile
  }
}
