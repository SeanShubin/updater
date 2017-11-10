package com.seanshubin.updater.domain

import java.nio.file.Path

class ConfiguredRunner(pomFileService: PomFileService,
                       updater: Updater,
                       path: Path) extends Runnable {
  override def run(): Unit = {
    val pomFiles = pomFileService.loadAllUnderPath(path)
    val pomFilesInNeedOfUpdate = pomFiles.filter(updater.inNeedOfUpdate)
    val updatedPomFiles = pomFilesInNeedOfUpdate.map(updater.update)
    updatedPomFiles.foreach(pomFileService.store)
  }
}
