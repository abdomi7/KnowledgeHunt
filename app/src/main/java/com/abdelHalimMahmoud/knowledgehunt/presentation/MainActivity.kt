package com.abdelHalimMahmoud.knowledgehunt.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import com.abdelHalimMahmoud.knowledgehunt.presentation.screens.mainScreen.AppMainScreen
import com.abdelHalimMahmoud.knowledgehunt.presentation.theme.KnowledgeHuntTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            KnowledgeHuntTheme {
                AppMainScreen()
            }
        }
    }
}

