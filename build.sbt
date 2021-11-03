import scala.collection.immutable.Seq

lazy val lwjglGroup    = "org.lwjgl"
lazy val lwjglArtifact = "lwjgl"
lazy val lwjglVersion  = "3.2.3"

lazy val os = Option(System.getProperty("os.name", ""))
  .map(_.substring(0, 3).toLowerCase) match {
  case Some("win") => "windows"
  case Some("mac") => "macos"
  case Some("lin") => "linux"
  case _           => throw new Exception("Unknown platform!")
}

lazy val root = (project in file("."))
  .settings(
    name := "HelloScala",
    version := "0.2-SNAPSHOT",
    scalaVersion := "2.13.7",
    idePackagePrefix := Some("pw.nidavellir.HelloScala"),
    resolvers += Resolver.sonatypeRepo("snapshots"),
    assembly / assemblyJarName := "helloscala.jar",
    libraryDependencies ++= Seq(
      lwjglGroup % lwjglArtifact            % lwjglVersion,
      lwjglGroup % s"$lwjglArtifact-stb"    % lwjglVersion,
      lwjglGroup % s"$lwjglArtifact-glfw"   % lwjglVersion,
      lwjglGroup % s"$lwjglArtifact-assimp" % lwjglVersion,
      lwjglGroup % s"$lwjglArtifact-opengl" % lwjglVersion,
      lwjglGroup % lwjglArtifact            % lwjglVersion classifier s"natives-$os",
      lwjglGroup % s"$lwjglArtifact-stb"    % lwjglVersion classifier s"natives-$os",
      lwjglGroup % s"$lwjglArtifact-glfw"   % lwjglVersion classifier s"natives-$os",
      lwjglGroup % s"$lwjglArtifact-assimp" % lwjglVersion classifier s"natives-$os",
      lwjglGroup % s"$lwjglArtifact-opengl" % lwjglVersion classifier s"natives-$os"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-encoding", "UTF-8",
      "-Xfatal-warnings",
      "-Ywarn-dead-code",
      "-target:jvm-1.8"
    ),
    javaOptions ++= {
      if (os == "macos")
        Seq("-XstartOnFirstThread")
      else
        Nil
    }
  )
