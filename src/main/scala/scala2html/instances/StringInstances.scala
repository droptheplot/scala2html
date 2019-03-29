package scala2html.instances

import scala2html.Stringable

trait StringInstances {
  implicit class scala2htmlString(private val v: String) extends Stringable {
    override def toString: String = v.toString
  }
}
