package com.example.noteapplication.android.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.utils.DateTimeUtil


@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: NoteModel,
    backgroundColor: Color,
    onNoteClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {

    val formattedDate = remember(note.timeStamp) {
        DateTimeUtil.formatNoteDate(note.timeStamp)
    }

    Column(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(5.dp))
            .padding(16.dp).clickable {
                onNoteClicked.invoke()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = note.title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
            Icon(imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier.clickable(
                    MutableInteractionSource(), null
                ) {
                    onDeleteClicked.invoke()
                })

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = note.content, fontWeight = FontWeight.Light, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate,
            color = Color.DarkGray,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.End)
        )
    }

}