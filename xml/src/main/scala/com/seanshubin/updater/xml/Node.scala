package com.seanshubin.updater.xml

import org.w3c.dom.{Node => DomNode}

class Node(domNode: DomNode) {
  def name: String = domNode.getNodeName

  def nodeType: NodeType = NodeType.fromCode(domNode.getNodeType)

  def value: String = domNode.getNodeValue

  def text: String = domNode.getTextContent

  def children: Seq[Node] = new NodeIterator(domNode.getChildNodes).toSeq

  def innerText:String = {
    val child = exactlyOne(children, "child of node")
    if(child.nodeType != NodeType.TextNode){
      throw new RuntimeException(s"expected child node to be of type ${NodeType.TextNode.name}")
    }
    child.text
  }

  def childElement(name:String):Node = {
    val matching = childElements(name)
    exactlyOne(matching, s"name is '$name'")
  }

  def exactlyOne[T](seq:Seq[T], criteria:String):T = {
    if(seq.size == 1){
      seq.head
    } else {
      throw new RuntimeException(s"expected exactly 1 element such that $criteria, got ${seq.size}")
    }
  }

  def childElements(name:String):Seq[Node] = {
    children.filter(_.nodeType == NodeType.ElementNode).filter(_.name == name)
  }

  def isEmptyText: Boolean = nodeType == NodeType.TextNode && text.trim == ""
}
