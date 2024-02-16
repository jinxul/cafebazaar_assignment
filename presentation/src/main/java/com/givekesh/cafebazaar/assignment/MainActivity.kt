package com.givekesh.cafebazaar.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.givekesh.cafebazaar.assignment.ui.discover.DiscoverView
import com.givekesh.cafebazaar.assignment.ui.theme.CafebazaarAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafebazaarAssignmentTheme {
                DiscoverView()
            }
        }
    }
}