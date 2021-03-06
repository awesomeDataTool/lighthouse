package be.dataminded.lighthouse.testing

import org.scalatest.{FunSuite, Tag}

case object SparkTest            extends Tag("be.dataminded.lighthouse.testing.SparkTest")
case object SparkIntegrationTest extends Tag("be.dataminded.lighthouse.testing.SparkIntegrationTest")

/**
  * Base class for testing Spark-based applications.
  */
class SparkFunSuite extends FunSuite with SharedSparkSession {

  def test(name: String)(body: => Any /* Assertion */ ): Unit = {
    test(name, SparkTest) {
      body
    }
  }
}

class SparkIntegrationFunSuite extends FunSuite with SharedSparkSession {

  def test(name: String)(body: => Any /* Assertion */ ): Unit = {
    test(name, SparkIntegrationTest) {
      body
    }
  }
}
