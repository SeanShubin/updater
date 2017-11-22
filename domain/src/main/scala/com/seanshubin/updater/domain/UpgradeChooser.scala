package com.seanshubin.updater.domain

trait UpgradeChooser {
  def selectUpgrade(dependency: Dependency, versions: Seq[Version]): Option[Version]
}
