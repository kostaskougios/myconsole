package f.model

import f.model.in.Input
import f.model.out.Output

case class InOut[S] private (
    in: Seq[Input],
    handlers: Seq[(S, String) => S],
    out: Seq[Output]
):
  val inAndHandler = in zip handlers
  def withInput(input: Input)(handler: (S, String) => S): InOut[S] = copy(in :+ input, handlers :+ handler, Nil)

object InOut:
  def forState[S] = InOut[S](Nil, Nil, Nil)
