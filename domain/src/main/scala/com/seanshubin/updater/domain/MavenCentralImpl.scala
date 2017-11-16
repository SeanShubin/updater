package com.seanshubin.updater.domain

import javax.servlet.http.HttpServletResponse

import com.seanshubin.http.values.domain.{RequestValue, Sender}

class MavenCentralImpl(mavenRepositoryUri: String,
                       fireUnableToFindDependencyInformation: (String, String) => Unit,
                       sender: Sender) extends MavenCentral {
  override def latestDependencyVersionsFor(dependencies: Seq[Dependency]): Map[Dependency, Version] = {
    val dependencyAndVersionSeq = for {
      dependency <- dependencies
      maybeLatestVersion = latestDependencyVersionFor(dependency)
      latestVersion <- maybeLatestVersion
    } yield {
      (dependency, latestVersion)
    }
    dependencyAndVersionSeq.toMap
  }

  private def latestDependencyVersionFor(dependency: Dependency): Option[Version] = {
    val dependencyInfoUri = mavenRepositoryUri + dependency.urlPath + "/maven-metadata.xml"
    val request = RequestValue(dependencyInfoUri, "GET", Seq(), Seq())
    val response = sender.send(request)
    if (response.statusCode == HttpServletResponse.SC_NOT_FOUND) {
      fireUnableToFindDependencyInformation("not found", dependencyInfoUri)
      None
    } else if (response.statusCode == HttpServletResponse.SC_OK) {
      ???
    } else {
      ???
    }
  }
}
