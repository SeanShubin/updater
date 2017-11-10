package com.seanshubin.updater.console

object EntryPoint extends App {
  new CommandLineArgumentsDependencyInjection {
    override def commandLineArguments: Array[String] = args
  }.runner.run()
}
