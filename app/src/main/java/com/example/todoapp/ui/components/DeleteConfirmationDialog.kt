package com.example.todoapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.todoapp.R

@Composable
fun DeleteConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text( stringResource(R.string.conf_supprimer)) },
        text = { Text( stringResource(R.string.conf_supprimer_msg)) },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                    onDismiss()
                }
            ) {
                Text( stringResource(R.string.btn_suppromer))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text( stringResource(R.string.btn_annuler))
            }
        }
    )
} 