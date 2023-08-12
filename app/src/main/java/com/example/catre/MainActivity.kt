package com.example.catre
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.catre.ViewModel.ToDoViewModel
import com.example.catre.Navigation.Navigation
import com.example.catre.ui.theme.CaTreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaTreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    window.statusBarColor = getColor(R.color.barbar)
                    val viewModel: ToDoViewModel = viewModel()
                    Navigation(viewModel = viewModel)
                }
            }
        }
    }
}
