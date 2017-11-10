package com.seanshubin.updater.domain

import java.nio.file.{Path, Paths}

class CommandLineRunner(args: Array[String],
                        runnerFactory: Path => Runnable) extends Runnable {
  override def run(): Unit = {
    val path = Paths.get(args(0))
    val runner = runnerFactory(path)
    runner.run()
  }
}
