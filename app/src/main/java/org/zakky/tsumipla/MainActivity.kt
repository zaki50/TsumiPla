package org.zakky.tsumipla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.zakky.tsumipla.ui.theme.TsumiPlaTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TsumiPlaTheme {
                val allScreens = RootScreen.values().toList()
                val navController = rememberNavController()
                val backstackEntry = navController.currentBackStackEntryAsState()
                val currentScreen = RootScreen.fromRoute(backstackEntry.value?.destination?.route)

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = allScreens,
                            currentScreen = currentScreen,
                            onTabSelected = { screen ->
                                navController.navigate(screen.name)
                            },
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = RootScreen.Main.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        RootScreen.values().forEach { screen ->
                            composable(screen.name) {
                                Text("${screen.name} screen")
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class RootScreen {
    Main,
    Search,
    ;

    companion object {
        fun fromRoute(route: String?): RootScreen = values().let { screens ->
            screens.find { it.name == route } ?: screens[0]
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<RootScreen>,
    currentScreen: RootScreen,
    onTabSelected: (screenName: RootScreen) -> Unit,
) {
    Text("nav bar")
}
