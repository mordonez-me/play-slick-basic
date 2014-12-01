import play.api.GlobalSettings
import play.api.Application
import play.api.Play.current
import com.typesafe.config._
import scala.slick.driver.MySQLDriver.simple._
import models._
 
object Global extends GlobalSettings{
 
  override def onStart(app: Application) {
    //Creamos una sesión
    globals.database.withSession { implicit session =>
      //Solo para el ejemplo hemos utilizado el try y catch para saber si las tablas fueron creadas
      try{
        //Creamos las tablas, los metodos ddl contienen los query de creación y el metodo create los ejecuta
        (Author.ddl ++ Book.ddl).create  

        //Creamos un registro de Author con el metodo +=
        val author = Author += Author(None, "Martin Odersky")
        //Creamos un registro de Author con el metodo += y pasamos la instancia de author como foreignKey
        Book += Book(None, "Programming in Scala", author)

      } catch {
        case _:Throwable => println("Tables already created")
      }
    }
  }
}

package object globals {
  
  //Cargamos la configuración definida en nuestro archivo application.conf
  val dbUrl = ConfigFactory.load().getString("db.default.url")
  val driver = ConfigFactory.load().getString("db.default.driver")
  val user = ConfigFactory.load().getString("db.default.user")
  val password = ConfigFactory.load().getString("db.default.pass")

  //Creamos una conexión al base de datos con los datos definidos en nuestro archivo application.conf
  lazy val database = Database.forURL(dbUrl, driver = driver, user = user, password = password)
 
}