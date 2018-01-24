name := "SnappyBenchmarking"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.xerial.snappy" % "snappy-java" % "1.1.7.1"

enablePlugins(JmhPlugin)