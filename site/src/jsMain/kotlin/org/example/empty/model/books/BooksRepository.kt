package model.books

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val API_ENDPOINT = "http://localhost:8080"

class BooksRepository {
    private val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    private suspend fun httpResponse(path: String): HttpResponse {
        println("### $path")
        val response: HttpResponse = client.get(API_ENDPOINT) {
            url {
                protocol = URLProtocol.HTTP
                host = "localhost"
                port = 8080
                path("/api/$path")
            }
        }

        println(response.call.request.url)
        return response
    }

    suspend fun getAllBooks(): List<Book> {
        try {
            val response: HttpResponse = httpResponse("books")
            return response.body()
        } catch (e: Exception) {
            println("###${e.cause?.message}")
        }
        val fakeList = mutableListOf(
            Book(1, "To Kill a Mockingbird", "Harper Lee"),
            Book(2, "1984", "George Orwell"),
        )

        return fakeList
    }
}