package scala2html

case class Tag(name: String,
               attrs: Seq[(String, String)],
               body: Seq[Stringable],
               empty: Boolean = false)
    extends Stringable {
  def >(body: Stringable*): Tag = copy(body = body)
  def `/>` : Tag = copy(empty = true)

  def ==(other: String): Boolean = toString == other

  override def toString: String =
    if (empty) {
      s"<$name${Tag.renderAttrs(attrs)} />"
    } else {
      s"<$name${Tag.renderAttrs(attrs)}>${Tag.renderBody(body)}</$name>"
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

  def renderBody(body: Seq[Stringable]): String = body.mkString
}
