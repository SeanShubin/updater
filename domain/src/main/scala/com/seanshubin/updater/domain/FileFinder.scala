package com.seanshubin.updater.domain

import java.nio.file.Path

trait FileFinder {
  def findByName(path: Path, name: String): Seq[Path]
}
