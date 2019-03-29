package scala2html.instances

import scala2html.Stringable

trait DoubleInstances {
  implicit class scala2htmlDouble(private val v: Double) extends Stringable {
    override def toString: String = v.toString
  }
}
