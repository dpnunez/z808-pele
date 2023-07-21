import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.window.rememberWindowState
import javax.swing.JFileChooser
import main.Sandbox
import main.VirtualMachine
import java.io.File

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    var counter by remember { mutableStateOf(0) }
    var filename by remember { mutableStateOf("") }


    val sb = Sandbox()
    sb.addRegister();

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "z808-pele")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    counter += 1
                    val selectedFile = selectFile()
                    if (selectedFile != null) {
                        filename = selectedFile.absolutePath
                    }
                }
            ) {
                Icon( 
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Increment",
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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
            Text("File: $filename")
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState()
    Window(state = windowState, onCloseRequest = ::exitApplication) {
        MaterialTheme {
            App()
        }
    }
}



fun selectFile(): File? {
    val chooser = JFileChooser()
    val result = chooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) {
        chooser.selectedFile
    } else null
}