package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import viewModel.books.BooksController
import model.books.BooksRepository

val appModule = module {
    singleOf(::BooksRepository)
    singleOf(::BooksController)
}