package com.giftthomu.DtreeTestApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.giftthomu.DtreeTestApp.ui.RecordsScreen
import com.giftthomu.DtreeTestApp.ui.theme.SearchBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchBarTheme {
                val viewModel = viewModel<RecordsViewModel>()
                RecordsScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}

val recordsList = listOf(
    "giftthomu",
    "giftthomu",
    "giftthomu",
    "catherinethomu"

)