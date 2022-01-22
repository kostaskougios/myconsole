package f.model.in

import f.model.InputOrOutput
import f.model.out.Output

trait Input extends InputOrOutput:
  def id: String
