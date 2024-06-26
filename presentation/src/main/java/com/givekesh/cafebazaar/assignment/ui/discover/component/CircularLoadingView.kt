package com.givekesh.cafebazaar.assignment.ui.discover.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.givekesh.cafebazaar.assignment.ui.theme.colorProgressBar

@Composable
fun CircularLoadingView(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier
            .width(32.dp)
            .height(31.dp),
        color = colorProgressBar,
    )
}

@Preview
@Composable
fun PreviewCircularLoadingView() {
    CircularLoadingView()
}