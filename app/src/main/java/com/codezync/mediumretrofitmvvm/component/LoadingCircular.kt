package com.codezync.mediumretrofitmvvm.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codezync.mediumretrofitmvvm.ui.theme.MediumRetrofitMVVMTheme

@Composable
fun LoadingCircular(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {

        val strokeWidth = 5.dp

        CircularProgressIndicator(
            modifier = Modifier.drawBehind {
                drawCircle(
                    Color.Red,
                    radius = size.width / 2 - strokeWidth.toPx() / 2,
                )
            },
            color = Color.LightGray,
            strokeWidth = strokeWidth
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingCircularPreview() {
    MediumRetrofitMVVMTheme {
        LoadingCircular()
    }
}