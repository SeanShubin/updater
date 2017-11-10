package com.seanshubin.updater.console

import java.nio.file.Path

import com.seanshubin.updater.domain._

trait CommandLineArgumentsDependencyInjection {
  def commandLineArguments: Array[String]

  val runnerFactory: Path => Runnable = thePath => {
    new ConfiguredDependencyInjection {
      override def path: Path = thePath
    }.runner
  }
  val runner: Runnable = new CommandLineRunner(commandLineArguments, runnerFactory)
}
