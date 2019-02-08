package scala2html

case class Tag(name: String,
               attrs: Seq[(String, String)],
               body: Seq[Tag],
               content: Option[String] = None,
               empty: Boolean = false) {
  def >(body: Tag*): Tag = copy(body = body)
  def >(content: String): Tag = copy(content = Some(content))
  def `/>` : Tag = copy(empty = true)

  def ==(other: String): Boolean = toString == other

  override def toString: String =
    if (empty) {
      s"<$name${Tag.renderAttrs(attrs)} />"
    } else {
      s"<$name${Tag.renderAttrs(attrs)}>${Tag.renderBody(body, content)}</$name>"
    }
}
object Tag {
  def renderAttrs(attrs: Seq[(String, String)]): String = attrs match {
    case Nil => ""
    case _ =>
      attrs
        .map(a => s"""${a._1}="${a._2}"""")
        .mkString(" ", " ", "")
  }

  def renderBody(body: Seq[Tag], content: Option[String]): String = body match {
    case Nil => content.getOrElse("")
    case _   => body.mkString
  }
}

object < {
  def apply(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Tag]())
  }
}

object </ {
  def apply(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Tag](), empty = true)
  }
}
