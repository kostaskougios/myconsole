package f.model.in

case class YorNQuestion(
    id: String,
    question: String,
    yesText: String = "Yes",
    noText: String = "No"
) extends Input
