package f.model.in

trait InputState[S <: InputState[S]]:
  def withAnswer(forInput: Input, answer: String): S
