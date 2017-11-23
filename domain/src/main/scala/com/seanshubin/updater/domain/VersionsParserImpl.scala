package com.seanshubin.updater.domain

import java.nio.charset.Charset

import com.seanshubin.updater.xml.Node

class VersionsParserImpl extends VersionsParser {
  override def parse(text: String, charset:Charset): Seq[String] = {
    val node = Node.fromString(text, charset)
    ???
  }
}
