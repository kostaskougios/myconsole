package f.model

import f.model.in.Input

case class Answers private (m: Map[Input, String]):
  def withAnswer(to: Input, answer: String) = new Answers(m + (to -> answer))
  def answer(of: Input): Option[String] = m.get(of)
  def all: Seq[(Input, String)] = m.toList

object Answers:
  def empty = Answers(Map.empty)
