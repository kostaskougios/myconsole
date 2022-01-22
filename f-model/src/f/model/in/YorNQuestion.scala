package f.model.in

case class YorNQuestion(
    id: String,
    question: String,
    yesText: String = "Y",
    noText: String = "N"
) extends Input
