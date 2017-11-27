package com.seanshubin.updater.domain

import com.seanshubin.updater.xml.{Node, NodeType}

class VersionsParserImpl extends VersionsParser {
  override def parse(text: String): Seq[String] = {
    val node = Node.fromString(text)
    val versionStrings = for {
      versionElement <- node.childElement("versioning", "versions").children
      if versionElement.nodeType == NodeType.ElementNode
      versionString = versionNodeToVersionString(versionElement)
    } yield {
      versionString
    }
    versionStrings
  }

  private def versionNodeToVersionString(node: Node): String = {
    if (node.name != "version") throw new RuntimeException("Element named version expected")
    val children = node.children
    if (children.size != 1) throw new RuntimeException("Exactly one child of version node expected")
    val child = children.head
    if (child.nodeType != NodeType.TextNode) throw new RuntimeException("text node expected")
    child.text
  }
}
