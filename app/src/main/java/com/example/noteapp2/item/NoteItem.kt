package com.example.noteapp2.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp2.database.NoteEntity
import com.example.noteapp2.ui.theme.primaryColor

@Composable
fun NoteItem(
    note: NoteEntity,
    delete: (expense: NoteEntity) -> Unit,
    update: (id:Int) -> Unit,

    ) {
    var isOpen = remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(5.dp))

    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){


        Column(

            modifier = Modifier.fillMaxSize().weight(0.8f)
                .border(
                    BorderStroke(1.dp, Gray),
                    shape = RoundedCornerShape(12.dp)).clickable {
                    update(note.id)

                }.padding(top = 10.dp, bottom = 10.dp)
            , verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (if (note.title != null) {
                    val words = note.title!!.split(" ")
                    if (words.size > 3) {
                        words.take(3).joinToString(" ") + " ..."
                    } else {
                        note.title
                    }
                } else {
                    ""
                }).toString(),
                fontWeight = FontWeight.W500,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 10.dp, 10.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = (if (note.description != null) {
                    val words = note.description!!.split(" ")
                    if (words.size > 3) {
                        words.take(3).joinToString(" ") + " ..."
                    } else {
                        note.description
                    }
                } else {
                    ""
                }).toString(),
                color = primaryColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

        }

        Column( modifier = Modifier.fillMaxSize().weight(0.2f)
            , verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            IconButton(onClick = { update(note.id) }, modifier = Modifier.padding(5.dp).border(
                BorderStroke(1.dp, Gray),
                shape = RoundedCornerShape(12.dp),)) {
                Icon(Icons.Rounded.Edit, contentDescription = null, tint = Gray)
            }
            IconButton(onClick = {
                isOpen.value = true }, modifier = Modifier.padding(5.dp).border(
                BorderStroke(1.dp, Gray),
                shape = RoundedCornerShape(12.dp),)) {
                Icon(Icons.Rounded.Delete, contentDescription = null, tint = Gray)
            }
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
                Text(text = "O'chirmoq")
            },
            // Content of the dialog
            text = {
                Text(text = "O'chirishni xoxlaysizmi?")
            },
            // Confirm button
            confirmButton = {
                Button(
                    onClick = {

                        isOpen.value = false
                        delete(note)
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
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red)

                ) {
                    Text(text = "Yo'q")
                }
            }
        )
    }
}