package com.seanshubin.updater.domain

import java.nio.file.Path

class FileFinderImpl(files: FilesContract,
                     directoriesToIgnore: Set[String],
                     searchingForFiles: (Path, String) => Unit,
                     fileFoundEvent: Path => Unit,
                     finishedFindingFiles: Seq[Path] => Unit) extends FileFinder {
  override def findByName(path: Path, name: String): Seq[Path] = {
    searchingForFiles(path, name)
    val acceptFile = acceptFileByName(name)
    val visitor = new CollectFilesVisitor(acceptFile, shouldScanDirectory, fileFoundEvent)
    files.walkFileTree(path, visitor)
    finishedFindingFiles(visitor.files)
    visitor.files
  }

  private def acceptFileByName(name: String): Path => Boolean = {
    path => path.getFileName.toString == name
  }

  private def shouldScanDirectory: Path => Boolean = {
    path => !directoriesToIgnore.contains(path.getFileName.toString)
  }
}
