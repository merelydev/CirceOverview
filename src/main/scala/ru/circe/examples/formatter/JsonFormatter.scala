package ru.circe.examples.formatter

import io.circe
import io.circe.jawn.decode
import io.circe.parser.parse
import io.circe.{Decoder, Encoder, Json, ParsingFailure}

object JsonFormatter {

  /**
   * This is a method to create Json from a string
   */
  def parseToJson(data: String): Either[ParsingFailure, Json] = parse(data)

  /**
   * This method creates a Json list
   * {{{
   * Use it if you have Json with a lot of similar data
   *
   * For example:
   *
   * [
   * {"car_id" : 1,"car_colour" : "red"},
   * {"car_id" : 2,"car_colour" : "blue"}
   * ]
   * }}}
   */
  def parseToJsonList(data: String): Either[ParsingFailure, List[Json]] =
    parse(data).map(json => json.asArray.toList.flatten)

  /**
   * This method converts an object to a string
   *
   * {{{
   * If you are using this method with automatic conversion add:
   * `import io.circe.generic.auto._`
   * }}}
   */
  def convertToJsonString[T: Encoder](obj: T): String = Encoder[T].apply(obj).noSpaces

  /**
   * This method converts a string to an object
   *
   * {{{
   * If you are using this method with automatic conversion add:
   * `import io.circe.generic.auto._`
   * }}}
   */
  def convertToObj[T: Decoder](data: String): Either[circe.Error, T] = decode[T](data)
}
