package com.seanshubin.updater.domain

import java.nio.file.Path

import com.seanshubin.http.values.domain.{RequestValue, ResponseValue}

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

  override def request(request: RequestValue): Unit = {
    //do nothing
  }

  override def response(request: RequestValue, response: ResponseValue): Unit = {
    emit(s"${response.statusCode} ${request.method} ${request.uri}")
  }
}
