package com.seanshubin.updater.domain

class UpdaterImpl(mavenCentral: MavenCentral) extends Updater {
  override def inNeedOfUpdate(pomFile: PomFile): Boolean = {
    val currentDependencyVersions = pomFile.dependencyVersions
    val latestDependencyVersions = mavenCentral.latestDependencyVersionsFor(currentDependencyVersions.keys.toSeq)

    def dependencyNeedsUpdate(dependency: Dependency): Boolean = {
      ???
    }

    latestDependencyVersions.keys.forall(dependencyNeedsUpdate)
  }

  override def update(pomFile: PomFile): PomFile = ???
}
