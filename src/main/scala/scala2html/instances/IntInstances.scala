package scala2html.instances

import scala2html.Stringable

trait IntInstances {
  implicit class scala2htmlInt(private val v: Int) extends Stringable {
    override def toString: String = v.toString
  }
}
