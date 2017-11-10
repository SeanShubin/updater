package com.seanshubin.updater.domain

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{FileVisitResult, FileVisitor, Path}

import scala.collection.mutable.ArrayBuffer

class CollectFilesVisitor(acceptFile: Path => Boolean,
                          shouldScanDirectory: Path => Boolean,
                          fileFoundEvent: Path => Unit) extends FileVisitor[Path] {
  private val filesBuffer = new ArrayBuffer[Path]
  val files: Seq[Path] = filesBuffer

  override def visitFileFailed(file: Path, exc: IOException): FileVisitResult = ???

  override def visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult = {
    if (acceptFile(file)) {
      fileFoundEvent(file)
      filesBuffer.append(file)
    }
    FileVisitResult.CONTINUE
  }

  override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult = {
    if (shouldScanDirectory(dir)) {
      FileVisitResult.CONTINUE
    } else {
      FileVisitResult.SKIP_SUBTREE
    }
  }

  override def postVisitDirectory(dir: Path, exc: IOException): FileVisitResult = FileVisitResult.CONTINUE
}
