package scala2html

trait Stringable {
  def toString: String
}

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

object Enrich {
  implicit class RichString(private val underlying: String) extends Stringable {
    override def toString: String = underlying.toString
  }

  implicit class RichInt(private val underlying: Int) extends Stringable {
    override def toString: String = underlying.toString
  }

  implicit class RichFloat(private val underlying: Float) extends Stringable {
    override def toString: String = underlying.toString
  }

  implicit class RichDouble(private val underlying: Double) extends Stringable {
    override def toString: String = underlying.toString
  }
}

object < {
  def apply(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Stringable]())
  }
}

object </ {
  def apply(n: String, a: (String, String)*): Tag = {
    Tag(n, a, Seq[Stringable](), empty = true)
  }
}
