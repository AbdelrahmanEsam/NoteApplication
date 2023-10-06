package com.example.noteapplication.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapplication.android.presentation.screen.NoteDetails
import com.example.noteapplication.android.presentation.screen.NotesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = getString(R.string.mainscreen)
                ) {
                    composable(getString(R.string.mainscreen)) {
                        NotesScreen(navController = navController)
                    }

                    composable(
                        getString(R.string.details) + "/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) {
                        val id = it.arguments!!.getLong("id")
                        NoteDetails(noteId = id, navController = navController)
                    }
                }
            }
        }
    }
}


