package com.givekesh.cafebazaar.assignment.ui.discover.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.givekesh.cafebazaar.assignment.R

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF131313),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier
                .width(79.dp)
                .height(88.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
        )
        CircularLoadingView(
            modifier = Modifier.padding(top = 50.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewLoadingView() {
    LoadingView()
}