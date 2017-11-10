package com.seanshubin.updater.domain

class PomFile {
  def dependencyVersions: Map[Dependency, Version] = ???
}

object PomFile {
  def fromString(text: String): PomFile = new PomFile
}