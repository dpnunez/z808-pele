import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import main.VirtualMachine
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeEditor(vm: VirtualMachine, text: String, onValueChange : (String) -> Unit) {

    Card(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
    ) {
        Text(text = "Code Editor")
        OutlinedTextField(
            placeholder = { Text("Digite seu código aqui") },
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            value = text,
            onValueChange = onValueChange,
            singleLine = false,
        )
    }

}