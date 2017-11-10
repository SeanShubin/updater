package com.seanshubin.updater.domain

case class Version(value: String) {
  def >(that: Version): Boolean = ???
}
