package com.herald.watchwise.presentation.screens.common_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import com.herald.watchwise.presentation.ui.theme.ColorBackground
import com.herald.watchwise.presentation.ui.theme.ColorPrimary
import kotlin.system.exitProcess

/**
 * A simple composable function that shows a dialog upon an error occurring,
 * it has to options whether to retry or exit the app
 * @param errorMessage the message of the error
 * @param retry function to be retried
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun DialogError(
    errorMessage: String,
    retry: () -> Unit
) {
    val context = LocalContext.current

    val isShowingMutable = mutableStateOf(true)

    if (isShowingMutable.value) {
        AlertDialog(
            onDismissRequest = {},
            dismissButton = {
                OutlinedButton(onClick = {
                    (context as ComponentActivity).finishAndRemoveTask()
                    exitProcess(0)
                }) {
                    Text(text = "Exit!")
                }
            },
            confirmButton = {
                OutlinedButton(onClick = {
                    isShowingMutable.value = false
                    retry()
                }) {
                    Text(text = "Retry!")
                }
            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = errorMessage)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = ColorBackground,
            contentColor = ColorPrimary
        )
    }
}