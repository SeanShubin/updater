package com.seanshubin.updater.domain

import java.nio.file.Path

trait PomFileService {
  def loadAllUnderPath(path: Path): Seq[PomFile]

  def store(pomFile: PomFile): Unit
}
