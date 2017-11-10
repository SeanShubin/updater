package com.seanshubin.updater.console

import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.Path

import com.seanshubin.updater.domain._

trait ConfiguredDependencyInjection {
  def path: Path

  val charset: Charset = StandardCharsets.UTF_8
  val directoriesToIgnore: Set[String] = Set("target", ".git")
  val files: FilesContract = FilesDelegate
  val emit: String => Unit = println
  val notifications: Notifications = new LineEmittingNotifications(emit)
  val fileFinder: FileFinder = new FileFinderImpl(
    files,
    directoriesToIgnore,
    notifications.searchingForFiles,
    notifications.pomFileFound,
    notifications.finishedFindingPomFiles
  )
  val pomFileService: PomFileService = new PomFileServiceImpl(fileFinder, files, charset)
  val mavenCentral: MavenCentral = new MavenCentralImpl()
  val updater: Updater = new UpdaterImpl(mavenCentral)
  val runner: Runnable = new ConfiguredRunner(pomFileService, updater, path)
}
