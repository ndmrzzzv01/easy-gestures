package com.ndmrzzzv.easygestures.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ndmrzzzv.easygestures.ui.screens.Screens
import com.ndmrzzzv.easygestures.ui.screens.choose_test.ChooseTestScreen
import com.ndmrzzzv.easygestures.ui.screens.choose_test.ChooseTestScreenActions
import com.ndmrzzzv.easygestures.ui.screens.choose_test.ChooseTestViewModel
import com.ndmrzzzv.easygestures.ui.screens.home.HomeScreen
import com.ndmrzzzv.easygestures.ui.screens.home.HomeScreenActions
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreen
import com.ndmrzzzv.easygestures.ui.screens.login.LoginScreenActions
import com.ndmrzzzv.easygestures.ui.screens.login.LoginViewModel
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountActions
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountScreen
import com.ndmrzzzv.easygestures.ui.screens.myaccount.MyAccountViewModel
import com.ndmrzzzv.easygestures.ui.screens.result.ResultScreenAction
import com.ndmrzzzv.easygestures.ui.screens.result.ResultViewModel
import com.ndmrzzzv.easygestures.ui.screens.result.ResultsScreen
import com.ndmrzzzv.easygestures.ui.screens.search.SearchScreen
import com.ndmrzzzv.easygestures.ui.screens.search.SearchScreenActions
import com.ndmrzzzv.easygestures.ui.screens.search.SearchViewModel
import com.ndmrzzzv.easygestures.ui.screens.splash.SplashScreen
import com.ndmrzzzv.easygestures.ui.screens.splash.SplashScreenActions
import com.ndmrzzzv.easygestures.ui.screens.tests.TestsScreen
import com.ndmrzzzv.easygestures.ui.screens.tests.TestsScreenActions
import com.ndmrzzzv.easygestures.ui.screens.tests.TestsViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.show.TestShowScreen
import com.ndmrzzzv.easygestures.ui.screens.tests.show.TestShowScreenActions
import com.ndmrzzzv.easygestures.ui.screens.tests.show.TestShowViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.write.TestWriteScreen
import com.ndmrzzzv.easygestures.ui.screens.tests.write.TestWriteViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.write.TestWriteScreenActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun EasyGesturesApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(Screens.SplashScreen.route) {
            val actions = SplashScreenActions.create(navController)
            SplashScreen(actions)
        }
        composable(Screens.LoginScreen.route) {
            val viewModel = koinViewModel<LoginViewModel>()
            val actions = LoginScreenActions.create(viewModel, navController)
            val authState = viewModel.authState.collectAsState().value
            val email = viewModel.email.collectAsState().value
            val password = viewModel.password.collectAsState().value
            val client = viewModel.getGoogleSignInClient()
            LoginScreen(actions, authState, client, email, password)
        }
        composable(Screens.HomeScreen.route) {
            val context = LocalContext.current
            val actions = HomeScreenActions.create(context, navController)
            HomeScreen(actions)
        }
        composable(Screens.SearchScreen.route) {
            val viewModel = koinViewModel<SearchViewModel>()
            val actions = SearchScreenActions.create(navController, viewModel)
            val state = viewModel.courses.collectAsState().value
            SearchScreen(actions, state)
        }
        composable(Screens.ChooseTestScreen.route) {
            val viewModel = koinViewModel<ChooseTestViewModel>()
            val actions = ChooseTestScreenActions.create(navController)
            val state = viewModel.lessons.collectAsState().value
            ChooseTestScreen(actions, state)
        }
        composable(Screens.TestsScreen.route) {
            val viewModel = koinViewModel<TestsViewModel>()
            val actions = TestsScreenActions.create(navController)
            TestsScreen(actions, viewModel.getLesson())
        }
        composable(Screens.WriteTestScreen.route) {
            val viewModel = koinViewModel<TestWriteViewModel>()

            val lesson = viewModel.getLesson()
            val questions = viewModel.getQuestionsInLesson()
            val userAnswers = viewModel.userAnswers.collectAsState().value

            val actions = TestWriteScreenActions.create(navController, viewModel)
            TestWriteScreen(lesson, questions, userAnswers, actions)
        }
        composable(Screens.ShowTestScreen.route) {
            val viewModel = koinViewModel<TestShowViewModel>()

            val lesson = viewModel.getLesson()
            val shuffledQuestions = viewModel.shuffledQuestions.collectAsState().value
            val imageUriMap = viewModel.imageUriMap
            val currentQuestion = viewModel.currentQuestion.collectAsState().value
            val currentUri = viewModel.currentUri.collectAsState().value

            val actions = TestShowScreenActions.create(navController, viewModel)
            TestShowScreen(actions, lesson, imageUriMap, currentQuestion, currentUri, shuffledQuestions)
        }
        composable(Screens.ResultsScreen.route) {
            val viewModel = koinViewModel<ResultViewModel>()
            val actions = ResultScreenAction.create(navController)
            val resultOfTest = viewModel.getResultOfTest()
            ResultsScreen(actions, resultOfTest)
        }
        composable(Screens.MyAccountScreen.route) {
            val context = LocalContext.current
            val viewModel = koinViewModel<MyAccountViewModel>()
            val actions = MyAccountActions.create(context, viewModel, navController)
            val userPhoto = viewModel.userImage.collectAsState().value
            MyAccountScreen(actions, viewModel.currentUser, userPhoto)
        }
    }
}