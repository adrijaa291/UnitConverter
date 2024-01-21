package com.example.unitconverter

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import androidx.compose.material3.Typography
//import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputvalue by remember { mutableStateOf(" ")}
    var outputvalue by remember { mutableStateOf(" ")}
    var inputunit by remember {mutableStateOf("Meters") }
    var outputunit by remember {mutableStateOf("Meters") }
    var iexpand by remember { mutableStateOf( false)}
    var oexpand by remember { mutableStateOf( false)}
    var conversionfactor = remember {mutableStateOf(1.00)}
    var oconversionfactor= remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Cursive,
        fontSize = 32.sp,
        color = Color.Red
    )


    fun conversion(){
        //?: ternary operator/ elvis operator
        val inputvaluedouble= inputvalue.toDoubleOrNull()?:0.0
        val result = (inputvaluedouble*conversionfactor.value*100.0/oconversionfactor.value).roundToInt()/100.0
        outputvalue=result.toString()
    }
    Column (modifier= Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Unit Converter", style = customTextStyle, /*modifier = Modifier.padding(12.dp*/)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputvalue,
            onValueChange = {inputvalue=it
                            conversion()},
            label = { Text(text = "Enter Value")}
            )
        Row {
            Box {
                Button(onClick = {iexpand=true}) {
                    Text(inputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = iexpand, onDismissRequest = {iexpand=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            iexpand = false
                            inputunit = "Centimeters"
                            conversionfactor.value = 0.01
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Meters")}, onClick = {
                            iexpand = false
                            inputunit = "Meters"
                            conversionfactor.value = 1.00
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Feet")}, onClick = {
                            iexpand = false
                            inputunit = "Feet"
                            conversionfactor.value = 0.3048
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Millimeters")}, onClick = {
                            iexpand = false
                            inputunit = "Millimeters"
                            conversionfactor.value = 0.001
                            conversion()
                        })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {oexpand=true}) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow down")
                }
                DropdownMenu(expanded = oexpand, onDismissRequest = {oexpand=false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")}, onClick = {
                            oexpand = false
                            outputunit = "Centimeters"
                            oconversionfactor.value = 0.01
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Meters")}, onClick = {
                            oexpand = false
                            outputunit = "Meters"
                            oconversionfactor.value = 1.00
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Feet")}, onClick = {
                            oexpand = false
                            outputunit = "Feet"
                            oconversionfactor.value = 0.3048
                            conversion()
                        })
                    DropdownMenuItem(
                        text = {Text("Millimeters")}, onClick = {
                            oexpand = false
                            outputunit = "Millimeters"
                            oconversionfactor.value = 0.001
                            conversion()
                        })
                }
            }
        }
        Spacer(modifier=Modifier.height(12.dp))
        Text("Result: $outputvalue $outputunit",
            style = MaterialTheme.typography.headlineMedium)

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}