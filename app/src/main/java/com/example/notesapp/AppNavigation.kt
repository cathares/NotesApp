package com.example.notesapp

import androidx.compose.runtime.Composable
import com.example.notesapp.presentation.ViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.notesapp.presentation.screens.ExitScreen
import com.example.notesapp.presentation.screens.LoginScreen
import com.example.notesapp.presentation.screens.NoteEditScreen
import com.example.notesapp.presentation.screens.NotesListScreen
import com.example.notesapp.presentation.screens.OnboardingScreen
import com.example.notesapp.presentation.screens.RegisterScreen
import org.koin.androidx.compose.koinViewModel


sealed class Screens(val route: String) {
    data object OnboardingScreen: Screens("onboarding")
    data object RegisterScreen: Screens("register")
    data object LoginScreen: Screens("login")
    data object NotesListScreen: Screens("list")
    data object NoteEditScreen: Screens("edit")
    data object ExitScreen: Screens("exit")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val appViewModel: ViewModel = koinViewModel()
    NavHost(
        navController = navController,
        startDestination = Screens.OnboardingScreen.route
    ) {
        composable(Screens.OnboardingScreen.route) {
            OnboardingScreen(
                onLoginClick = { navController.navigate(Screens.LoginScreen.route) },
                onRegisterClick = {
                    navController.navigate(Screens.RegisterScreen.route)
                }
            )
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(
                viewModel = appViewModel,
                onClick = {
                    navController.navigate(Screens.NotesListScreen.route){
                        popUpTo(Screens.OnboardingScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screens.RegisterScreen.route) {
            RegisterScreen(
                viewModel = appViewModel,
                onClick = { navController.navigate(Screens.LoginScreen.route) }
            )
        }
        composable(Screens.NotesListScreen.route) {
            NotesListScreen(
                viewModel = appViewModel,
                onNoteClick = { navController.navigate(Screens.NoteEditScreen.route)},
                onExitClick = { navController.navigate(Screens.ExitScreen.route) },
                onNewNoteClick = { navController.navigate(Screens.NoteEditScreen.route) }
            )
        }
        composable(Screens.NoteEditScreen.route) {
            NoteEditScreen(
                viewModel = appViewModel,
                onSaveClick = {
                    navController.navigate(Screens.NotesListScreen.route) {
                        popUpTo(Screens.OnboardingScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                              },
                onBackClick = {
                    navController.navigate(Screens.NotesListScreen.route) {
                        popUpTo(Screens.OnboardingScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable(Screens.ExitScreen.route) {
            ExitScreen(
                viewModel = appViewModel,
                onClick = {
                    navController.navigate(Screens.OnboardingScreen.route) {
                        popUpTo(Screens.OnboardingScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}