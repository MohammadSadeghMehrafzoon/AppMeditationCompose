package ir.misterdeveloper.aramkadeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import ir.misterdeveloper.aramkadeapplication.di.myModules
import ir.misterdeveloper.aramkadeapplication.ui.features.main.MainScreen
import ir.misterdeveloper.aramkadeapplication.ui.features.meetings.MeetingScreen

import ir.misterdeveloper.aramkadeapplication.ui.theme.AramkadeApplicationTheme
import ir.misterdeveloper.aramkadeapplication.util.MyScreen
import org.koin.android.ext.koin.androidContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)

            }) {
                AramkadeApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AramkadeUi()
                    }
                }

            }
        }
    }


    @Composable
    fun AramkadeUi() {

        val navController = rememberNavController()
        KoinNavHost(
            navController = navController,
            startDestination = MyScreen.MainScreen.rout
        ) {
            composable(MyScreen.MainScreen.rout) {
                MainScreen()
            }

            composable(
                MyScreen.MeetingScreen.rout +"/{id}/{title}",
                listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val meetingId = backStackEntry.arguments?.getString("id")
                val title = backStackEntry.arguments?.getString("title")
                MeetingScreen(meetingId?: "0", title ?: "no_name")
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AramkadeApplicationTheme {
            Greeting("Android")
        }
    }

}