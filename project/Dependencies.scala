import sbt._

object Dependencies {

  private lazy val amazonSdkVersion = "1.11.238"
  private lazy val sparkVersion     = "2.3.1"

  lazy val sparkCore = "org.apache.spark" %% "spark-core"  % sparkVersion % Provided
  lazy val sparkSql  = "org.apache.spark" %% "spark-sql"   % sparkVersion % Provided
  lazy val sparkHive = "org.apache.spark" %% "spark-hive"  % sparkVersion % Provided
  lazy val sparkMlib = "org.apache.spark" %% "spark-mllib" % sparkVersion % Provided

  lazy val scopt          = "com.github.scopt"           %% "scopt"          % "3.7.0"
  lazy val typesafeConfig = "com.typesafe"               % "config"          % "1.3.3"
  lazy val logback        = "ch.qos.logback"             % "logback-classic" % "1.2.3"
  lazy val scalaLogging   = "com.typesafe.scala-logging" %% "scala-logging"  % "3.9.0"
  lazy val cats           = "org.typelevel"              %% "cats-core"      % "1.2.0"
  lazy val betterFiles    = "com.github.pathikrit"       %% "better-files"   % "3.4.0"

  // Database connectivity
  lazy val scalikejdbc = "org.scalikejdbc" %% "scalikejdbc" % "3.2.4"
  lazy val h2          = "com.h2database"  % "h2"           % "1.4.197" % Test

  // Amazon AWS
  lazy val awsSdkS3  = "com.amazonaws" % "aws-java-sdk-s3" % amazonSdkVersion % Provided
  lazy val awsSdkSsm = "com.amazonaws" % "aws-java-sdk-ssm" % amazonSdkVersion % Provided
  lazy val amazonSdk = Seq(awsSdkS3, awsSdkSsm)

  lazy val scalaTest        = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val testDependencies = Seq(scalaTest % Test, h2)

  lazy val commonDependencies: Seq[ModuleID] =
    Seq(sparkCore, sparkSql, sparkHive, scopt, betterFiles, scalaLogging, scalikejdbc) ++ amazonSdk ++ testDependencies

}
