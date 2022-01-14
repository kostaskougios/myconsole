package f.model.out

import f.model.in.Input

case class Ask(input: Input)(onAnswer: String => Output) extends Output
