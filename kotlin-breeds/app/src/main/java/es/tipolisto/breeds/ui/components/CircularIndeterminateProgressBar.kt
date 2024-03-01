package es.tipolisto.breeds.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyCircularProgressIndicator(isDisplayed:Boolean) {
    if (isDisplayed) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp,Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally

        ){
            CircularProgressIndicator(modifier = Modifier.size(100.dp),color=MaterialTheme.colorScheme.primary)
            Text(
                text = "Wait, loading animal list...",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 300.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MyCircularProgressIndicatorPreview(){
    MyCircularProgressIndicator(true)
}