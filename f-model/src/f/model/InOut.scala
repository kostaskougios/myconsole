package f.model

import f.model.in.Input
import f.model.out.Output

case class InOut(
    io: Seq[InputOrOutput]
)

object InOut:
  def apply(io: InputOrOutput): InOut = InOut(Seq(io))
  val Done = InOut(Nil)
