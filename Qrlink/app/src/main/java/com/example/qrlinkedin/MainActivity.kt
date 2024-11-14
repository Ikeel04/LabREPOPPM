package com.example.qrlinkedin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.qrlinkedin.ui.theme.QrlinkTheme
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QrlinkTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    QRGeneratorScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun QRGeneratorScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var qrCodeUrl by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Generador de QR de LinkedIn")

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)
                .padding(horizontal = 8.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val baseUrl = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="
            qrCodeUrl = "${baseUrl}https://www.linkedin.com/in/$username"
        }) {
            Text(text = "Generar QR")
        }

        Spacer(modifier = Modifier.height(16.dp))

        qrCodeUrl?.let { url ->
            val painter: Painter = rememberAsyncImagePainter(url)
            Image(
                painter = painter,
                contentDescription = "Código QR",
                modifier = Modifier.size(150.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QRGeneratorPreview() {
    QrlinkTheme {
        QRGeneratorScreen()
    }
}
