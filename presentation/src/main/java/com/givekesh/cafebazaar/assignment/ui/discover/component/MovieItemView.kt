package com.givekesh.cafebazaar.assignment.ui.discover.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie

@Composable
fun MovieItemView(
    modifier: Modifier = Modifier,
    item: Movie,
    onItemClick: (Movie) -> Unit,
) {
    Column(
        modifier = modifier.clickable { onItemClick(item) }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(119.dp)
                .height(154.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = item.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally),
            text = item.title,
            style = TextStyle(
                color = Color(0xFFCBC8C8),
                fontSize = 14.sp,
                fontWeight = FontWeight(600),
            ),
            maxLines = 1,
        )
    }
}

@Preview
@Composable
private fun PreviewMovieItemView() {
    MovieItemView(
        item = Movie(
            id = 1,
            title = "Test",
            posterPath = "",
        ),
        onItemClick = {},
    )
}