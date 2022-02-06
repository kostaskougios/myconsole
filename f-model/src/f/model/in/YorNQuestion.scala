package f.model.in

case class YorNQuestion(
    id: String,
    question: String,
    yesText: String = "Y",
    noText: String = "N"
) extends Input:
  def isYes(in: String): Boolean = in.equalsIgnoreCase(yesText)
  def isNo(in: String): Boolean = in.equalsIgnoreCase(noText)
