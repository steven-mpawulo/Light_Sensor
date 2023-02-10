package com.example.lightsensor.ui.screens

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LightSensorScreen() {
    val sensorManager = LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    var sensorValue by remember{ mutableStateOf("0") }
    val lightSensorEventListener = object: SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                if(event.sensor.type == Sensor.TYPE_LIGHT){
                    sensorValue = event.values[0].toString()
                }
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            TODO("Not yet implemented")
        }

    }
    sensorManager.registerListener(lightSensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
   Scaffold(
       topBar = {
           TopAppBar() {
               Text(text = "Light Sensor", textAlign = TextAlign.Center)
           }
       }
   ){paddingValues ->
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally,
       ) {
           Text(text = "Light Sensor Value")
           Spacer(modifier = Modifier.height(8.dp))
           Text(text = "$sensorValue lx", style = TextStyle(fontSize = 32.sp))
       }

   }
}