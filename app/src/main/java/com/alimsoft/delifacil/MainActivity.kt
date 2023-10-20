package com.alimsoft.delifacil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.alimsoft.delifacil.ui.theme.DeliFacilTheme
import com.alimsoft.common.ui.composable.ErrorSnackbarHost
import com.alimsoft.home.graph.homeGraph
import com.alimsoft.login.graph.LoginDestination
import com.alimsoft.login.graph.loginGraph
import com.alimsoft.navigation.Navigator
import org.koin.compose.rememberKoinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val navigator: Navigator = rememberKoinInject()

            DeliFacilTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
                    bottomBar = {

                    },
                    snackbarHost = {
                        ErrorSnackbarHost(
                            hostState = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    },
                    modifier = Modifier.navigationBarsPadding()
                ) { padding ->
                    val navController = rememberNavController()

                    NavigationEffects(topLevelRoutes = emptyList(), navController = navController, navigator = navigator)
                    NavHost(
                        navController,
                        startDestination = LoginDestination.route(),
                        modifier = Modifier.padding(padding)
                    ) {
                        homeGraph(navController)
                        loginGraph(navController)
                    }
                }
            }
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
    DeliFacilTheme {
        Greeting("Android")
    }
}