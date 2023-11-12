package view.navigation

sealed class Screen {
    object BooksList : Screen()
    object BookDetails: Screen()
}