package com.seanshubin.updater.domain

import javax.servlet.http.HttpServletResponse

import com.seanshubin.http.values.domain.{RequestValue, ResponseValue, Sender}

class MavenCentralImpl(mavenRepositoryUri: String,
                       fireUnableToFindDependencyInformation: String => Unit,
                       sender: Sender,
                       versionsParser: VersionsParser,
                       upgradeChooser: UpgradeChooser,
                       fireRequest: RequestValue => Unit,
                       fireResponse: (RequestValue, ResponseValue) => Unit) extends MavenCentral {
  override def chooseUpgradeDependencyVersionsFor(dependencies: Seq[Dependency]): Map[Dependency, Version] = {
    val dependencyAndVersionSeq = for {
      dependency <- dependencies
      maybeVersionForUpgrade = chooseVersionForUpgrade(dependency)
      versionForUpgrade <- maybeVersionForUpgrade
    } yield {
      (dependency, versionForUpgrade)
    }
    dependencyAndVersionSeq.toMap
  }

  private def chooseVersionForUpgrade(dependency: Dependency): Option[Version] = {
    val dependencyInfoUri = mavenRepositoryUri + dependency.urlPath + "/maven-metadata.xml"
    val request = RequestValue(dependencyInfoUri, "GET", Seq(), Seq())
    fireRequest(request)
    val response = sender.send(request)
    fireResponse(request, response)
    if (response.statusCode == HttpServletResponse.SC_NOT_FOUND) {
      fireUnableToFindDependencyInformation(dependencyInfoUri)
      None
    } else if (response.statusCode == HttpServletResponse.SC_OK) {
      val versionStrings = versionsParser.parse(response.text)
      val versions = versionStrings.map(Version.fromString)
      val maybeVersionForUpgrade = upgradeChooser.selectUpgrade(dependency, versions)
      maybeVersionForUpgrade
    } else {
      ???
    }
  }
}
