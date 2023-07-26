import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import main.Register

@Composable
fun SingleRegister(name: String, value: Short, isBinary: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$name:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(end = 8.dp),
            style = MaterialTheme.typography.labelMedium
        )

        // Format value
        val displayValue = if (isBinary) {
            value.toString(2).padStart(16, '0')
        } else {
            "0x" + value.toString(16).toUpperCase()
        }

        Text(
            text = displayValue,
            style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily.Monospace)
        )
    }
}

@Composable
fun RegisterPreview(registers: MutableMap<Short, Register>) {
    var isBinary by remember { mutableStateOf(true) }

    Card(
        modifier = Modifier.width(300.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Mostrar em binário", style = MaterialTheme.typography.bodyMedium)
                Switch(checked = isBinary, onCheckedChange = { isBinary = it }, )
            }

            Divider()

            // Display each register
            registers.values.forEachIndexed { index, reg ->
                SingleRegister(value = reg.value, name = reg.name, isBinary = isBinary)

                // Add divider between registers, but not after the last one
                if (index < registers.size - 1) {
                    Divider()
                }
            }
        }
    }
}
