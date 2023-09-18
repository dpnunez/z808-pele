import androidx.compose.animation.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
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
    var currentFiles: Array<File>? by remember { mutableStateOf(null) }
    var lastrun: String? by remember { mutableStateOf(null) }
    var virtualMachine: VirtualMachine by remember { mutableStateOf(VirtualMachine()) }
    var currentText: String by remember { mutableStateOf("") }

    fun handleRun() {
        if (currentFiles != null) {
            virtualMachine = VirtualMachine()
            virtualMachine?.loadProgram(currentFiles)
            virtualMachine?.run()
            lastrun = concatCurrentFilesName(currentFiles)
        }
    }

    fun handleChangeTextEditor(newValue: String) {
        currentText = newValue
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "z808-pele")
                }
            )
            // Mostrar os nomes dos arquivos contidos em currentFiles
            // Se currentFiles for null, mostrar "Nenhum arquivo carregado"
            // Se currentFiles for diferente de null, mostrar o nome do arquivo

            if (currentFiles != null) {
                Text(text = concatCurrentFilesName(currentFiles) ?: "Nenhum arquivo carregado")
            } else {
                Text(text = "Nenhum arquivo carregado")
            }
        },
        floatingActionButton = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                FloatingActionButton(
                    modifier = Modifier.alpha(if (currentFiles == null) 0.5f else 1f),
                    onClick = {
                        handleRun()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.PlayArrow,
                        contentDescription = "Play",
                    )
                }
//                ExtendedFloatingActionButton(
//                    modifier = Modifier.alpha(if (currentText == "") 0.5f else 1f),
//                    icon = {
//                        Icon(
//                            imageVector = Icons.Rounded.Build,
//                            contentDescription = "Assemble",
//                        )
//                    },
//                    text = {
//                        Text("Assemble")
//                    },
//                    onClick = {
//                        virtualMachine.assemble(currentText)
//                    }
//                )
                ExtendedFloatingActionButton(
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Play",
                        )
                    },
                    text = {
                        Text("Load Files")
                    },
                    onClick = {
                        val selectedFile = selectFile()
                        if (selectedFile != null) {
                            currentFiles = selectedFile
                        }
                    }
                )


            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Row(modifier = Modifier.padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp) ) {
                Box(modifier = Modifier.weight(1f)) {
                    MemoryPreview(vm = virtualMachine, text = currentText, onValueChange = ::handleChangeTextEditor)
                }

                Box(modifier = Modifier.width(400.dp)) {
                    RegisterPreview(virtualMachine.cpu.registers.registers)
                }
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

fun selectFile(): Array<File>? {
    val chooser = JFileChooser()
    chooser.isMultiSelectionEnabled = true;
    val filter = FileNameExtensionFilter("Assembly Files", "asm")
    chooser.fileFilter = filter

    val result = chooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) {
        for(file in chooser.selectedFiles) {
            println(file.name)
        }

        // Ordernar os arquivos por ordem alfabética
        chooser.selectedFiles.sort()
        chooser.selectedFiles
    } else null
}

fun concatCurrentFilesName(currentFiles: Array<File>?): String {
    var result = ""
    var i = 1
    if (currentFiles != null) {
        for (file in currentFiles) {
            result += i.toString() + ". " + file.name + "    "
            i++
        }
    }
    return result
}


