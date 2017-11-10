package com.seanshubin.updater.domain

trait MavenCentral {
  def latestDependencyVersionsFor(dependencies: Seq[Dependency]): Map[Dependency, Version]
}
