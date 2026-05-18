package com.example.hastakalaassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var selectedScreen by remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen == "home",
                    onClick = { selectedScreen = "home" },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = selectedScreen == "add",
                    onClick = { selectedScreen = "add" },
                    label = { Text("Add") },
                    icon = { Icon(Icons.Default.Add, null) }
                )
                NavigationBarItem(
                    selected = selectedScreen == "analytics",
                    onClick = { selectedScreen = "analytics" },
                    label = { Text("Analytics") },
                    icon = { Icon(Icons.Default.Assessment, null) }

                )
                NavigationBarItem(
                    selected = selectedScreen == "ai",
                    onClick = { selectedScreen = "ai" },
                    label = { Text("AI") },
                    icon = { Icon(Icons.Default.SmartToy, null) }
                )
            }
        }
    ) { padding ->

        when (selectedScreen) {
            "home" -> DashboardScreen()
            "add" -> AddSaleScreen()
            "analytics" -> AnalyticsScreen()
            "ai" -> AIScreen()
        }
    }
}