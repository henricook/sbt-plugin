package org.scalasteward.core.util

import org.scalacheck.Gen
import org.scalatest.{FunSuite, Matchers}
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class stringTest extends FunSuite with Matchers with ScalaCheckPropertyChecks {
  test("splitBetweenLowerAndUpperChars(s).mkString == s") {
    forAll(Gen.asciiStr) { s: String =>
      string.splitBetweenLowerAndUpperChars(s).mkString shouldBe s
    }
  }

  test("splitBetweenLowerAndUpperChars: substrings end with lower case char or all are upper case") {
    forAll(Gen.asciiStr) { s: String =>
      string
        .splitBetweenLowerAndUpperChars(s)
        .forall(sub => sub.matches(".*\\p{javaLowerCase}") || sub.matches("\\p{javaUpperCase}*"))
    }
  }
}
