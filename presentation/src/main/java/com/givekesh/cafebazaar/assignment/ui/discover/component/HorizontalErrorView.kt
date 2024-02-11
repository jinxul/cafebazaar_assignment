package com.givekesh.cafebazaar.assignment.ui.discover.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.givekesh.cafebazaar.assignment.R

@Composable
fun HorizontalErrorView(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRetryClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.55f),
            text = errorMessage,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
        )
        OutlinedButton(
            modifier = Modifier.height(36.dp),
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                width = 1.dp,
                color = Color(0xFF44464E),
            ),
            onClick = onRetryClick,
        ) {
            Text(
                text = stringResource(R.string.try_again),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHorizontalErrorView() {
    HorizontalErrorView(
        errorMessage = "Something went",
        onRetryClick = {},
    )
}