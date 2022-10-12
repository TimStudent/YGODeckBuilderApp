package com.example.ygodeckbuilder

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.ygodeckbuilder.ui.theme.YGODeckBuilderTheme
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YGODeckBuilderTheme {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    IntroText()
                    IntroImage()
                    supportActionBar?.hide()
                }
            }
        }
    }
}

@Composable
fun IntroText() {
    val white = Color(0xFFFFFFFF)
    val black = Color(0xFF000000)
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = white,
            text = "Welcome to the world of Yu-Gi-Oh!",
        )
        Button(
            enabled = true,
            onClick = { context.startActivity(Intent(context, MainActivity2::class.java)) }) {
            Text(text = "Build Your Deck!")
        }
    }
}

@Composable
fun IntroImage() {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = rememberAsyncImagePainter("https://static.wikia.nocookie.net/vsdebating/images/d/d6/BlueEyesWhiteDragonAlternate3.png/revision/latest?cb=20210314215010"),
        contentDescription = null,
        alignment = Alignment.TopCenter
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    YGODeckBuilderTheme {
        IntroText()
        IntroImage()
    }
}