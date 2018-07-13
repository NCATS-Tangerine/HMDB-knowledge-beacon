// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.3")
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.21.0"
libraryDependencies += "javax.el" % "javax.el-api" % "2.2.4"