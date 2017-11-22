package com.seanshubin.updater.domain

case class Version(value: String)

object Version {
  def fromString(value: String): Version = new Version(value)
}