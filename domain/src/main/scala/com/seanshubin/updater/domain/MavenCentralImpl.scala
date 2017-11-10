package com.seanshubin.updater.domain

class MavenCentralImpl extends MavenCentral {
  override def latestDependencyVersionsFor(dependencies: Seq[Dependency]): Map[Dependency, Version] = ???
}
