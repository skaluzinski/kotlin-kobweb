package view.books

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.theme.colors.ColorScheme
import com.varabyte.kobweb.silk.theme.colors.ColorSchemes
import model.books.Book
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Text

@Composable
fun BookDetailsScreen(book: Book?, navigateBack: () -> Unit) {
    Column(
        modifier = Modifier.flexWrap(FlexWrap.Wrap).onClick { navigateBack() }.padding(all = 20.px).background(
            ColorSchemes.Brown._700),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row {
            Text("go back")
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("BookAuthor")
            Text(book?.author ?: "")
        }
        Row {
            Text("book name")
            Text(book?.title ?: "")
        }
        Row {
            Text("book id")
            Text(book?.id.toString() ?: "")
        }

        repeat(3) {
            Row {
                Text("addional book info to come : $it")
                Text("blabla $it")

            }
        }
    }
}