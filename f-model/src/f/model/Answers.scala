package f.model

import f.model.in.Input

case class Answers private (m: Map[String, String]):
  def withAnswer(to: Input, answer: String) = new Answers(m + (to.id -> answer))
  def answer(of: Input): Option[String] = m.get(of.id)

object Answers:
  def empty = Answers(Map.empty)
