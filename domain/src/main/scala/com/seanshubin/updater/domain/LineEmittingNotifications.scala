package com.seanshubin.updater.domain

import java.nio.file.Path

class LineEmittingNotifications(emit: String => Unit) extends Notifications {
  override def pomFileFound(path: Path): Unit = {
    emit(s"pom file found: $path")
  }

  override def finishedFindingPomFiles(files: Seq[Path]): Unit = {
    emit(s"${files.size} files found")
  }

  override def searchingForFiles(path: Path, name: String): Unit = {
    emit(s"searching for files named '$name' in path $path")
  }

  override def fireUnableToFindDependencyInformation(uri: String): Unit = {
    emit(s"Unable to find dependency information at $uri")
  }
}
