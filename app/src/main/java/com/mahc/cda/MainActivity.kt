package com.mahc.cda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mahc.cda.ui.compose.ListScreen
import com.mahc.cda.ui.theme.CdaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CdaTheme {
                ListScreen()
            }
        }
    }
}