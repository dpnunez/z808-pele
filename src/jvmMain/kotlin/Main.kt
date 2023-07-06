import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import main.Sandbox
import main.VirtualMachine

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    var counter by remember { mutableStateOf(0) }


    val sb = Sandbox()
    sb.andDirect();

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "z808-pele")
                }
            )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {
                    counter += 1;
                }
            ) {
                Icon( 
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Increment",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { paddingValues ->
        Box(Modifier.fillMaxSize().padding(paddingValues)) {
            AnimatedContent(
                modifier = Modifier.align(Alignment.Center),
                targetState = counter,
                transitionSpec = {
                    // https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedcontent
                    slideInVertically() + fadeIn() with slideOutVertically() + fadeOut()
                }
            ) { value ->
                Text("${VirtualMachine().name}: $value")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        // https://developer.android.com/jetpack/compose/designsystems/material3
        MaterialTheme {
            App()
        }
    }
}
