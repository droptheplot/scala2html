package scala2html.instances

import scala2html.Stringable

trait FloatInstances {
  implicit class scala2htmlFloat(private val v: Float) extends Stringable {
    override def toString: String = v.toString
  }
}
