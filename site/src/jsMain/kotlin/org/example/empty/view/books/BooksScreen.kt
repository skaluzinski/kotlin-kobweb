package view.books

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.BackgroundColor
import com.varabyte.kobweb.compose.css.CSSBackground
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import model.books.Book
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Text
import viewModel.books.BooksController

@Composable
fun BooksScreen(booksController: BooksController, navigateToBookDetails: (Book) -> Unit) {
    val allBooks by booksController.books.collectAsState()
    println(allBooks)
    Column(modifier = Modifier.fillMaxSize().backgroundColor(ColorSchemes.Monochrome._600)) {
        Row( modifier = Modifier.fillMaxWidth().height(48.vh)) {
            Text(value = "GO BACK TO Weather List ${allBooks.size}")
        }

        Box(modifier = Modifier.fillMaxSize().backgroundColor(BackgroundColor.Unset)) {
            BooksList(books = allBooks, onBookClick = navigateToBookDetails)
        }
    }
}

@Composable
private fun BooksList(books: List<Book>, onBookClick: (Book) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().scrollBehavior(ScrollBehavior.Auto),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        books.forEach { book ->
            BookCard(
                book = book,
                onClick = { onBookClick(book) }
            )
        }
    }
}

@Composable
private fun BookCard(book: Book, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.px)
            .onClick { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text("Book Name:")
                Text(book.title)
            }
            Row {
                Text("Book Author:")
                Text(book.author)
            }
        }
    }
}