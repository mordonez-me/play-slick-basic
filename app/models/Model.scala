package models

//Import necesario para exponer las clases que necesitamos para nuestros modelos
import scala.slick.driver.MySQLDriver.simple._

//Case class que servirá para representar a nuestros registros de la tabla Author de la base de datos.
case class Author(id:Option[Int], name:String)

/* Representación para Slick de una tabla en la base de datos. Contiene las columnas 
y un valor * necesario para Slick con el mismo tipo de las columnas de la tabla */

class AuthorTable(tag: Tag) extends Table[Author](tag, "Author") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def * = (id.?, name) <> ((Author.apply _).tupled, Author.unapply)
}

//Objeto Author con el cual haremos las operaciones en la base de datos
object Author extends TableQuery(new AuthorTable(_))

case class Book(id:Option[Int], name:String, authorId:Int)
class BookTable(tag: Tag) extends Table[Book](tag, "Book") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def authorId = column[Int]("authorId")
    def * = (id.?, name, authorId) <> ((Book.apply _).tupled, Book.unapply)

    //Llave foranea para la tabla Author.
    def author = foreignKey("author_fk", authorId, Author)(_.id)
}
object Book extends TableQuery(new BookTable(_))