package be.dataminded.lighthouse.testing

import org.scalatest.FunSpec

case class Person(name: String, age: Int)

class DatasetComparerSpec extends FunSpec with SharedSparkSession with DatasetComparer {

  import spark.implicits._

  describe("assertDatasetEquality") {
    it("does nothing true if the Datasets have the same schemas and content") {
      val actual   = Seq(Person("bob", 1), Person("frank", 5)).toDS
      val expected = Seq(Person("bob", 1), Person("frank", 5)).toDS

      assertDatasetEquality(actual, expected)
    }

    it("can performed unordered Dataset comparisons") {
      val actual   = Seq(Person("bob", 1), Person("frank", 5)).toDS
      val expected = Seq(Person("frank", 5), Person("bob", 1)).toDS

      assertDatasetEquality(actual, expected, orderedComparison = false)
    }

    it("throws an error for unordered Dataset comparisons that don't match") {
      val actual   = Seq(Person("bob", 1), Person("frank", 5)).toDS
      val expected = Seq(Person("frank", 5), Person("bob", 1), Person("sadie", 2)).toDS

      intercept[AssertionError] {
        assertDatasetEquality(actual, expected, orderedComparison = false)
      }
    }

    it("returns false if the Dataset content is different") {
      val actual   = Seq(Person("bob", 1), Person("frank", 5)).toDS
      val expected = Seq(Person("sally", 66), Person("sue", 54)).toDS

      intercept[AssertionError] {
        assertDatasetEquality(actual, expected)
      }
    }
  }

  describe("assertDatasetEquality using DataFrames") {
    it("does nothing if the DataFrames have the same schemas and content") {
      val actual   = Seq(1, 5).toDF("number")
      val expected = Seq(1, 5).toDF("number")

      assertDatasetEquality(actual, expected)
    }

    it("assert fails if the DataFrames have different schemas") {
      val actual   = Seq(1, 5).toDF("number")
      val expected = Seq((1, "hi"), (5, "bye")).toDF("number", "word")

      intercept[AssertionError] {
        assertDatasetEquality(actual, expected)
      }
    }

    it("throws an error if the DataFrames content is different") {
      val actual   = Seq(1, 5).toDF("number")
      val expected = Seq(10, 5).toDF("number")

      intercept[AssertionError] {
        assertDatasetEquality(actual, expected)
      }
    }

    it("can performed unordered DataFrame comparisons") {
      val actual   = Seq(1, 5).toDF("number")
      val expected = Seq(5, 1).toDF("number")

      assertDatasetEquality(actual, expected, orderedComparison = false)
    }

    it("throws an error for unordered DataFrame comparisons that don't match") {
      val actual   = Seq(1, 5).toDF("number")
      val expected = Seq(5, 2).toDF("number")

      intercept[AssertionError] {
        assertDatasetEquality(actual, expected)
      }
    }
  }
}
