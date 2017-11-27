package com.seanshubin.updater.domain

trait VersionsParser {
  def parse(text: String): Seq[String]
}
