package com.example.noteapp2.screens.add

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
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
fun AddView(viewmodel: AddViewModel, navController: NavController) {
    val text = viewmodel.title.observeAsState().value!!
    val amount = viewmodel.description.observeAsState().value!!

    Column(
        Modifier
            .fillMaxSize().background(primarybackground), verticalArrangement = Arrangement.SpaceBetween
    ) {
        CenterAlignedTopAppBar(title = { Text(text = "Yangi", fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = "")
            }
        })
        Column(Modifier.padding(horizontal = 12.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp,top = 10.dp),
                onValueChange = { viewmodel.editText(it) },
                value = if (text == "") "" else text,
                label = { Text("Nomi",    color = Color(168,175,185))},
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
                modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp,top = 10.dp),
                value = if (amount == "") "" else amount,
                onValueChange = {viewmodel.editAmount(it) },
                label = { Text("Izoh",    color = Color(168,175,185))},
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
            enabled = viewmodel.note.description != amount || viewmodel.note.title != text,
                    onClick = { viewmodel.add(NoteEntity(title = text, description = amount)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.textButtonColors(
                containerColor = primaryColor,
                disabledContainerColor = secondaryColor
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Qo'shish", color = Color.White, fontSize = 20.sp)
        }
    }
}