package controllers

import play.api._
import play.api.mvc._
import globals.database
import scala.slick.driver.MySQLDriver.simple._
import models._

object Application extends Controller {

  def index = Action {

    database.withSession { implicit session =>

      //Obtenemos la lista de libros de la base de datos
      val books = (for(b <- Book) yield b).list

      //Enviamos la lista a la vista para que se muestre
      Ok(views.html.index(books))
    }   
  }

}