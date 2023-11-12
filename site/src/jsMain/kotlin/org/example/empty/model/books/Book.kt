package model.books

import kotlinx.serialization.Serializable

@Serializable
data class Book(val id: Int? = null, var title: String, var author: String)


