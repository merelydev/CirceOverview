package ru.circe.examples

import io.circe
import io.circe.generic.auto._
import io.circe.{Json, ParsingFailure}
import ru.circe.examples.formatter.JsonFormatter
import ru.circe.examples.implicits.ExampleClasses.{Actor, PersonInfo}

object CirceApp extends App {

  println(s"""
         |${"-" * 100}
         |Parsing Demonstration
         |${"-" * 100}
         |""".stripMargin)

  val simplyStr: String =
    """
        |{
        |    "name": "Leonardo Dicaprio",
        |    "sex":  "male",
        |    "films": [
        |      "Titanic",
        |      "The Wolf of Wall Street",
        |      "The Revenant"
        |    ]
        |  }
        |""".stripMargin

  val parseJsonRes: Either[ParsingFailure, Json] = JsonFormatter.parseToJson(simplyStr)

  println("Json:\n")

  println(parseJsonRes)

  println("-" * 100)

  val listSimplyStr: String =
    """
      |[
      |{
      |    "name": "Leonardo Dicaprio",
      |    "sex":  "male",
      |    "films": [
      |      "Titanic",
      |      "The Wolf of Wall Street",
      |      "The Revenant"
      |    ]
      |  },
      |  {
      |    "name": "Chloe Grace Moretz",
      |    "sex":  "female",
      |    "films": [
      |      "The Equalizer",
      |      "Carrie",
      |      "Movie 43"
      |    ]
      |  }
      |  ]
      |""".stripMargin

  println("\nJson List:\n")
  val parseJsonListRes = JsonFormatter.parseToJsonList(listSimplyStr)

  println(parseJsonListRes)

  println("-" * 100)
  println(s"""
       |${"-" * 100}
       |Converter Demonstration
       |${"-" * 100}
       |""".stripMargin)

  val actor: Actor =
    Actor("Leonardo Dicaprio", PersonInfo("male", 46, None))
  // Encoding
  val actorJson: String = JsonFormatter.convertToJsonString[Actor](actor)

  val infoJson: String = JsonFormatter.convertToJsonString[PersonInfo](actor.info)

  println("Convert Obj to Json String:\n")

  println(actorJson)
  println(infoJson)

  // Decoding
  val actorObj: Either[circe.Error, Actor] = JsonFormatter.convertToObj[Actor](actorJson)

  val infoObj: Either[circe.Error, PersonInfo] = JsonFormatter.convertToObj[PersonInfo](infoJson)

  println("\nConvert String to Obj:\n")

  println(actorObj)
  println(infoObj)

  println("-" * 100)
}
