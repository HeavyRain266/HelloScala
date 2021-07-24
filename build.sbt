import scala.collection.immutable.Seq

name := "helloScala"

version := "0.2"

scalaVersion := "2.13.6"

idePackagePrefix := Some("io.github.HeavyRain266.helloScala")

lazy val lwjglVersion = "3.2.3"

lazy val os = Option(System.getProperty("os.name", ""))
  .map(_.substring(0, 3).toLowerCase) match {
  case Some("win") => "windows"
  case Some("mac") => "macos"
  case _           => "linux"
}

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.lwjgl" % "lwjgl"        % lwjglVersion,
  "org.lwjgl" % "lwjgl-stb"    % lwjglVersion,
  "org.lwjgl" % "lwjgl-glfw"   % lwjglVersion,
  "org.lwjgl" % "lwjgl-assimp" % lwjglVersion,
  "org.lwjgl" % "lwjgl-opengl" % lwjglVersion,
  "org.lwjgl" % "lwjgl"        % lwjglVersion classifier s"natives-$os",
  "org.lwjgl" % "lwjgl-stb"    % lwjglVersion classifier s"natives-$os",
  "org.lwjgl" % "lwjgl-glfw"   % lwjglVersion classifier s"natives-$os",
  "org.lwjgl" % "lwjgl-assimp" % lwjglVersion classifier s"natives-$os",
  "org.lwjgl" % "lwjgl-opengl" % lwjglVersion classifier s"natives-$os"
)

javaOptions ++= {
  if (os == "macos")
    Seq("-XstartOnFirstThread")
  else
    Nil
}

scalacOptions ++= Seq(
  "-deprecation",
  "encoding", "UTF-8",
  "-target:jvm-1.8"
)