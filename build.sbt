lazy val root = (project in file(".")).
  settings(
    name := "apache-storm-scala-seed",
    version := "0.1.0",
    scalaVersion := "2.11.4",
    mainClass in Compile := Some("stormscala.Main")
  )

resolvers += Resolver.sonatypeRepo("public")
resolvers += "Clojars" at "http://clojars.org/repo/"

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-math3" % "3.6.1",
  "org.apache.storm" % "storm-core" % "1.1.1" 
)
