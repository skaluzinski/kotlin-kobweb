package org.example.empty

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import di.appModule
import model.books.Book
import model.books.BooksRepository

import org.jetbrains.compose.web.css.*
import org.koin.core.context.startKoin
import view.books.BookDetailsScreen
import view.books.BooksScreen
import view.navigation.Screen
import viewModel.books.BooksController

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    // Configure silk here
}


@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            var screenState: Screen by remember { mutableStateOf(Screen.BooksList) }
            var choosenBook: Book? by remember { mutableStateOf(null) }

            val controller = BooksController(BooksRepository())

            when(screenState) {
                Screen.BooksList -> BooksScreen(
                    booksController = controller,
                ) {
                    choosenBook = it
                    screenState = Screen.BookDetails
                }

                Screen.BookDetails -> BookDetailsScreen(
                    book = choosenBook,
                    navigateBack = { screenState = Screen.BooksList },
                )
            }
        }
    }
}
