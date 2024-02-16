package com.givekesh.cafebazaar.assignment.ui.discover.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.givekesh.cafebazaar.assignment.R
import com.givekesh.cafebazaar.assignment.ui.theme.colorError

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 96.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .size(96.dp)
                    .background(
                        color = Color(0xFF092040),
                        shape = CircleShape,
                    )
                    .padding(24.dp),
                painter = painterResource(id = R.drawable.ic_sad),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.connection_glitch),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                )
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.no_internet_error),
                style = TextStyle(
                    color = colorError,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                    lineHeight = TextUnit(24f, TextUnitType.Sp) //24.sp
                ),
            )
            Button(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .width(100.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C3051)
                ),
                shape = RoundedCornerShape(24.dp),
                onClick = onRetryClick,
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewErrorView() {
    ErrorView(
        onRetryClick = {},
    )
}