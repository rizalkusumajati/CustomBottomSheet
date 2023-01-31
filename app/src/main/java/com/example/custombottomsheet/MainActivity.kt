package com.example.custombottomsheet

import CustomModalBottomSheetLayout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.custombottomsheet.ui.theme.CustomBottomSheetTheme
import kotlinx.coroutines.launch
import rememberModalBottomSheetState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomBottomSheetTheme {
                val bottomSheetState = rememberModalBottomSheetState(
                    initialValue = CustomModalBottomSheetValue.Hidden
                )
                val coroutineScope = rememberCoroutineScope()
                val constraintScope = this
                CustomModalBottomSheetLayout(
                    sheetState = bottomSheetState,
                    sheetContent = {

                        Column(Modifier.verticalScroll(rememberScrollState()).padding(bottom = 40.dp)) {
                            Text(text = "Hello BottomSheet")
                            Button(onClick = { coroutineScope.launch {bottomSheetState.hide() } }) {
                                Text(text = "Hide BottomSheet")
                            }
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        Column {
                            Button(onClick = { 
                                coroutineScope.launch {
                                    bottomSheetState.show()
                                }
                            }) {
                                Text(text = "Show BottomSheet")
                            }
                        }
                    }
                }
                // A surface container using the 'background' color from the theme
                
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomBottomSheetTheme {
        Greeting("Android")
    }
}