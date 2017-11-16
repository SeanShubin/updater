package com.seanshubin.updater.domain

case class Dependency(group: String, artifact: String) {
  def urlPath: String = "/" + dotToSlash(group) + "/" + artifact

  private def dotToSlash(s: String): String = s.replaceAll("\\.", "/")
}
