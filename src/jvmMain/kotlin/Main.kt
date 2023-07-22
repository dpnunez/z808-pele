import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter
import main.VirtualMachine
import java.io.File

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
@Preview

fun App() {
    var currentFile: File? by remember { mutableStateOf(null) }
    var lastrun: String? by remember { mutableStateOf(null) }
    var virtualMachine: VirtualMachine by remember { mutableStateOf(VirtualMachine()) }

    fun handleRun() {
        if (currentFile != null) {
            virtualMachine = VirtualMachine()
            virtualMachine?.loadProgram(currentFile)
            virtualMachine?.run()
            lastrun = currentFile?.absolutePath
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "z808-pele")
                }
            )
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                FloatingActionButton(
                    modifier = Modifier.alpha(if (currentFile == null) 0.5f else 1f),
                    onClick = {
                        handleRun()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Play",
                    )
                }
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Play",
                        )
                    },
                    text = {
                        Text("Carregar Arquivo")
                    },
                    onClick = {
                        val selectedFile = selectFile()
                        if (selectedFile != null) {
                            currentFile = selectedFile
                        }
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(Modifier.fillMaxSize().padding(paddingValues)) {
            Row( horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp) ) {
                Row {
                    Text("File: $currentFile")
                    Text("Lastrun: $lastrun")
                }
                RegisterPreview(virtualMachine.cpu.registers.registers)
            }
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState()
    Window(state = windowState, onCloseRequest = ::exitApplication) {
        MaterialTheme( colorScheme = darkColorScheme()) {
            App()
        }
    }
}

fun selectFile(): File? {
    val chooser = JFileChooser()
    val filter = FileNameExtensionFilter("Text Files", "txt")
    chooser.fileFilter = filter

    val result = chooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) {
        chooser.selectedFile
    } else null
}