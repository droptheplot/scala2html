package scala2html

import org.scalatest.{Assertion, FunSuite}

import scala.language.postfixOps

import scala2html.implicits._

class TagTest extends FunSuite {
  def assertTag(a: Tag, b: String): Assertion = {
    assert(a == b)
  }

  test("tag") {
    assertTag(
      <("div"),
      "<div></div>"
    )
  }

  test("tag with attribute") {
    assertTag(
      <("div", ("class", "container")),
      """<div class="container"></div>""",
    )
  }

  test("tag with multiple attributes") {
    assertTag(
      <("div", ("class", "container"), ("id", "1")),
      """<div class="container" id="1"></div>"""
    )
  }

  test("tag with nested tag") {
    assertTag(
      <("div") >
        <("span"),
      """<div><span></span></div>"""
    )
  }

  test("tag with multiple nested tags") {
    assertTag(
      <("div") > (
        <("span"),
        <("a")
      ),
      """<div><span></span><a></a></div>""",
    )
  }

  test("tag with string") {
    assertTag(
      <("div") > "hello",
      """<div>hello</div>""",
    )
  }

  test("tag with int") {
    assertTag(
      <("div") > 123,
      """<div>123</div>""",
    )
  }

  test("tag with float") {
    assertTag(
      <("div") > 123.0f,
      """<div>123.0</div>""",
    )
  }

  test("tag with double") {
    assertTag(
      <("div") > 123.0,
      """<div>123.0</div>""",
    )
  }

  test("empty tag") {
    assertTag(
      <("img") />,
      """<img />""",
    )

    assertTag(
      </("img"),
      """<img />""",
    )
  }

  test("empty tag with attributes") {
    assertTag(
      <("img", ("src", "image")) />,
      """<img src="image" />""",
    )

    assertTag(
      </("img", ("src", "image")),
      """<img src="image" />""",
    )
  }

  test("everything") {
    assertTag(
      <("html", ("lang", "en")) > (
        <("head") > (
          <("title") > "title"
        ),
        <("body") > (
          <("div") > (
            <("img", ("src", "image")) />
          )
        )
      ),
      """<html lang="en"><head><title>title</title></head><body><div><img src="image" /></div></body></html>""",
    )
  }
}
