package kcats.chapter1

import arrow.extension

// https://books.underscore.io/scala-with-cats/scala-with-cats.html#the-type-class

sealed class Json
data class JsObject(val value: Map<String, Json>) : Json()
data class JsString(val value: String) : Json()
data class JsNumber(val value: Number) : Json()
object JsNull : Json()

interface JsonWriter<T> {
    fun T.write(): Json
}

data class Person(val name: String, val email: String) {
    companion object
}

// manual implementation of a typeclass
interface StringJsonWriter : JsonWriter<String> {
    override fun String.write(): Json =
            JsString(this)
}

fun String.Companion.jsonWriter() = object : StringJsonWriter {}

// parts of code can be generated using the @extension annotation
@extension
interface PersonJsonWriter : JsonWriter<Person> {
    override fun Person.write(): Json =
            JsObject(mapOf(
                    "name" to String.jsonWriter().run { name.write() },
                    "email" to JsString(this.email)))
}
