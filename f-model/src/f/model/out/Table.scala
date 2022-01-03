package f.model.out

case class Table(
    table: Seq[Seq[Any]]
) extends Output

object Table:
  def apply(headers: Seq[String], rows: Seq[Seq[Any]]): Table = Table(Seq(headers) ++ rows)

  def products[A <: Product](rows: Seq[A]): Table =
    if rows.isEmpty then Table(Nil)
    else
      val headers = rows.head.productElementNames.toSeq
      apply(headers, rows.map(_.productIterator.toSeq))
