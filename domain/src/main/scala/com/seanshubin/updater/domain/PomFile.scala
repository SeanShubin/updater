package com.seanshubin.updater.domain

import java.io.InputStream
import javax.xml.parsers.{DocumentBuilder, DocumentBuilderFactory}

import com.seanshubin.updater.xml.Node
import org.w3c.dom.Document

class PomFile(xmlNode: Node) {
  def dependencyVersions: Map[Dependency, Version] = {
    val dependenciesNodes = xmlNode.childElements("dependencies")
    val dependencyManagementNodes = xmlNode.childElements("dependencyManagement").flatMap(_.childElements("dependencies"))
    val parentNodes = dependenciesNodes ++ dependencyManagementNodes

    val dependencyVersionTuples = for {
      parentNode <- parentNodes
      dependency <- parentNode.childElements("dependency")
    } yield {
      toDependencyVersionTuple(dependency)
    }
    val map = dependencyVersionTuples.toMap
    map.foreach(println)
    map
  }

  def toDependencyVersionTuple(node: Node): (Dependency, Version) = {
    val dependency = nodeToDependency(node)
    val version = nodeToVersion(node)
    (dependency, version)
  }

  def nodeToDependency(node: Node): Dependency = {
    val groupId = node.childElement("groupId").innerText
    val artifactId = node.childElement("artifactId").innerText
    Dependency(groupId, artifactId)
  }

  def nodeToVersion(node: Node): Version = {
    val version = node.childElement("version").innerText
    Version(version)
  }
}

object PomFile {
  private val documentBuilderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance
  private val documentBuilder: DocumentBuilder = documentBuilderFactory.newDocumentBuilder

  def fromInputStream(inputStream: InputStream): PomFile = {
    val document: Document = documentBuilder.parse(inputStream)
    val root: Node = new Node(document.getDocumentElement)
    new PomFile(root)
  }
}