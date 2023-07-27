import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import main.VirtualMachine
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeEditor(vm: VirtualMachine, text: String, onValueChange : (String) -> Unit) {

    Column() {
        Card(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Code Editor")
            OutlinedTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = false,
            )
        }
    }

}