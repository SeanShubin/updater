package com.seanshubin.updater.domain

trait MavenCentral {
  def chooseUpgradeDependencyVersionsFor(dependencies: Seq[Dependency]): Map[Dependency, Version]
}
