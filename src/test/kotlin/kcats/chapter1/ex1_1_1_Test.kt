package kcats.chapter1

import io.kotlintest.specs.DescribeSpec
import kcats.chapter1.person.jsonWriter.jsonWriter

class Ex1_1_1Test : DescribeSpec({

    describe("can write") {
        it("foo") {
            val person = Person("name", "email")

            Person.jsonWriter().run {
                println(person.write())
            }
        }
    }
})
