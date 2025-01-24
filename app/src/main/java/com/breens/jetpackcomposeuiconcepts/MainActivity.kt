package com.breens.jetpackcomposeuiconcepts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.breens.jetpackcomposeuiconcepts.navigation.TaskNavHost
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .safeDrawingPadding()
            ){
                TaskManagerAppJetpackComposeTheme {
                    val fileName = "username.txt"
                    val file = File(filesDir, fileName)
                    if (!file.exists()) {
                        file.createNewFile()
                    }
                    val userName = "insertedUserName" // Replace with the actual user name
                    val fileContent = file.readText()
                    if (userName in fileContent) {
                        // User name exists in the file
                    } else {
                        // User name does not exist in the file
                        file.appendText("$userName\n")
                    }
                    TaskNavHost()
                }
            }

        }
    }
}