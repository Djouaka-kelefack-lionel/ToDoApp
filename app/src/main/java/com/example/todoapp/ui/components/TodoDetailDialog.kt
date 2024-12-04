package com.example.todoapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.data.TodoItem
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoDetailDialog(
    todo: TodoItem,
    onDismiss: () -> Unit,
    onEdit: () -> Unit
) {
    val dateFormat = SimpleDateFormat( stringResource(id = R.string.date), Locale.getDefault())

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.text_detail),
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column {
                    Text(
                        text = "Titre",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = todo.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }

                if (todo.description.isNotEmpty()) {
                    Column {
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = todo.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                Column {
                    Text(
                        text = stringResource(R.string.text_statut),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = if (todo.isCompleted) stringResource(R.string.termine_msg) else stringResource(R.string.encour_msg),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (todo.isCompleted) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurface
                    )
                }

                Column {
                    Text(
                        text = stringResource(R.string.text_date_creation),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = dateFormat.format(Date(todo.timestamp)),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onEdit) {
                Text( stringResource(R.string.btn_modifier))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text( stringResource(R.string.btn_fermer))
            }
        }
    )
} 