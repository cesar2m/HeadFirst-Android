package com.cesar2m.temperatureconvert

import android.health.connect.datatypes.units.Temperature
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cesar2m.temperatureconvert.ui.theme.TemperatureConvertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemperatureConvertTheme {
                    Surface {
                        MainActivityContent()
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityContent() {
    val celsius = remember { mutableStateOf(0) }
    val newCelcius = remember { mutableStateOf("") }

    Column (modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Header(R.drawable.sunrise, "Amanecer")
        EnterTemperature(newCelcius.value) { newCelcius.value = it }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            ConvertButton {
                newCelcius.value.toIntOrNull()?.let {
                    celsius.value = it
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center){
                TemperaturaText(celsius.value)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit){
    TextField(
        value = temperature,
        label = {Text("Temperatura en °C")},
        onValueChange =  changed,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Header(image: Int, description: String){
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TemperaturaText(celsius: Int){
    val farenheit = (celsius.toDouble()*9/5)+32
    Text("$celsius °C is $farenheit °F",
        modifier = Modifier.padding(42.dp),
        fontSize = 32.sp
    )

}

@Composable
fun ConvertButton(clicked: () -> Unit ){
    Button(onClick = clicked){
        Text("Convert")
    }
}



