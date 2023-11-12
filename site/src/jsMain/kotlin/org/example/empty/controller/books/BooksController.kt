package viewModel.books

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import model.books.Book
import model.books.BooksRepository

class BooksController(booksRepository: BooksRepository) {
    private val scope = CoroutineScope(SupervisorJob())

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books = _books.stateIn(scope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        scope.launch {
            _books.emit(booksRepository.getAllBooks())
            println(booksRepository.getAllBooks())
        }
    }
}