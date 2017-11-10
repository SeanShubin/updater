package com.seanshubin.updater.domain

import java.nio.file.Path

trait Notifications {
  def searchingForFiles(path: Path, name: String): Unit

  def pomFileFound(path: Path): Unit

  def finishedFindingPomFiles(files: Seq[Path]): Unit

}
