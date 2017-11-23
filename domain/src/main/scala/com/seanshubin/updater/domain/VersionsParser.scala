package com.seanshubin.updater.domain

import java.nio.charset.Charset

trait VersionsParser {
  def parse(text: String, charset:Charset): Seq[String]
}
