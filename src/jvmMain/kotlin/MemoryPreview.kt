import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed

import androidx.compose.material3.*
import androidx.compose.runtime.*
import main.VirtualMachine
import androidx.compose.ui.Modifier


val itemsList = (0..5).toList()
val itemsIndexedList = listOf("A", "B", "C")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryPreview(vm: VirtualMachine, text: String, onValueChange : (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
    ) {
        Text(text = "Memory")
        LazyVerticalGrid(
            columns = GridCells.Fixed(6)
        ) {
            itemsIndexed(vm.memory.cells.toList()) { index, item ->
                Text("$item",)
            }

        }
    }

}