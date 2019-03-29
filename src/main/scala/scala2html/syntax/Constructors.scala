package scala2html.syntax

import scala2html.{Stringable, Tag}

trait Constructors {
  def <(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Stringable]())
  }

  def </(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Stringable](), empty = true)
  }
}
