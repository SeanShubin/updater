package com.seanshubin.updater.domain

import java.nio.file.Path

import com.seanshubin.http.values.domain.{RequestValue, ResponseValue}

trait Notifications {
  def searchingForFiles(path: Path, name: String): Unit

  def pomFileFound(path: Path): Unit

  def finishedFindingPomFiles(files: Seq[Path]): Unit

  def fireUnableToFindDependencyInformation(uri: String): Unit

  def request(request: RequestValue): Unit

  def response(request: RequestValue, response: ResponseValue): Unit
}
