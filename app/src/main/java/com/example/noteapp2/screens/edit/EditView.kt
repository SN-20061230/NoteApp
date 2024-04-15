package com.example.noteapp2.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noteapp2.database.NoteEntity
import com.example.noteapp2.ui.theme.primaryColor
import com.example.noteapp2.ui.theme.primarybackground
import com.example.noteapp2.ui.theme.secondaryColor
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(viewModel: EditViewModel, navController: NavController) {
    val text1 = viewModel.title.observeAsState().value!!
    val amount1 = viewModel.description.observeAsState().value!!
    var isOpen = remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize().background(primarybackground), verticalArrangement = Arrangement.SpaceBetween
    ) {
        CenterAlignedTopAppBar(title = { Text(text = "Tahrirlash",  fontWeight = FontWeight.SemiBold)}, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "")
            }
        })
        Column(Modifier.padding(horizontal = 12.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp,top = 10.dp),
                onValueChange = { viewModel.updateText(it) },
                value = if (text1 == "") "" else text1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                ),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = "Nomini kiriting", color = Gray) },
                leadingIcon = {
                    Icon(Icons.Rounded.Edit, contentDescription = null, tint = Gray)
                },
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp, top = 10.dp),
                value = if (amount1 == "") "" else amount1,
                onValueChange = {viewModel.updateAmount(it) },
                placeholder = { Text(text = "Izohni kiriting", color = Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                ),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = primaryColor,
                ),
                leadingIcon = {
                    Icon(Icons.Rounded.Done, contentDescription = null, tint = Gray)
                }
            )
        }
        TextButton(
            enabled = viewModel.note.description != amount1 || viewModel.note.title != text1,
            onClick = {
                isOpen.value = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = primaryColor,
                disabledContainerColor = secondaryColor
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Saqlash", color = Color.White, fontSize = 20.sp)
        }
    }


    if (isOpen.value) {
        AlertDialog(
            onDismissRequest = {
                // Set the dialog to be closed when dismissed
                isOpen.value = false
            },
            // Title of the dialog
            title = {
                Text(text = "Tahrirlash")
            },
            // Content of the dialog
            text = {
                Text(text = "Aniq o'zgartirmoqchimisiz?")
            },
            // Confirm button
            confirmButton = {
                Button(
                    onClick = {

                        isOpen.value = false
                        viewModel.update(NoteEntity(title = text1, description = amount1))
                    },
                    colors = ButtonDefaults.buttonColors(Color.Green)

                ) {
                    Text(text = "Ha")
                }
            },
            dismissButton = {
                Button(
                    onClick = {

                        isOpen.value = false
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)

                ) {
                    Text(text = "Yo'q")
                }
            }
        )
    }
}