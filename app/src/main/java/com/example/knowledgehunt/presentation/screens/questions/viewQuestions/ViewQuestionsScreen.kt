package com.example.knowledgehunt.presentation.screens.questions.viewQuestions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.knowledgehunt.presentation.components.QuestionCardItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import compose.icons.TablerIcons
import compose.icons.tablericons.CodePlus

@Composable
fun ViewQuestionsScreen(navController: NavController) {
    val viewModel: ViewQuestionsViewModel = hiltViewModel()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Icon(
                imageVector = TablerIcons.CodePlus,
                tint = MaterialTheme.colors.primaryVariant,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable {
//                        navController.navigate(Screens.AddQuestion.route)
                    },
            )
        },
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                viewModel.refresh()
            },
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {

                items(viewModel.questionState.value) { question ->

                    QuestionCardItem(
                        question,
                        navController = navController,
                        click = {

//                            navController.navigate(Screens.ArticleDetails.route)
                        }
                    )
                }
            }
        }
    }
}