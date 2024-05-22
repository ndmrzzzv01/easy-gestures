package com.ndmrzzzv.easygestures.ui.screens.myaccount

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseUser
import com.ndmrzzzv.easygestures.R

@Composable
fun MyAccountScreen(
    actions: MyAccountActions,
    currentUser: FirebaseUser?
) {

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        alpha = 0.5f
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        var name by remember { mutableStateOf(currentUser?.displayName ?: "") }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.welcome_my_account),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF531549)
        )

        Divider(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 16.dp),
            thickness = 1.dp,
            color = Color(0xFFB284AB)
        )

        AsyncImage(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(70.dp)
                .clip(CircleShape)
                .fillMaxWidth()
                .clickable {
                    actions.changePhoto()
                },
            model = currentUser?.photoUrl,
            contentDescription = "avatar",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = currentUser?.email ?: "",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF531549)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    modifier = Modifier,
                    text = "Your name",
                    fontFamily = FontFamily.Serif,
                )
            },
        )
        ElevatedButton(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            onClick = {
                actions.updateInfoAboutUser()
            },
            border = BorderStroke(1.dp, Color(0xFFC060B1))
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = "Update Info",
                fontFamily = FontFamily.Serif,
                color = Color(0xFF531549)
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = "Your Progress..",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF531549)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            PeriodProgress(
                modifier = Modifier.weight(1f),
                progress = 35f,
                textProgress = "Today"
            )
            PeriodProgress(
                modifier = Modifier.weight(1f),
                progress = 10f,
                textProgress = "Last week"
            )
            PeriodProgress(
                modifier = Modifier.weight(1f),
                progress = 100f,
                textProgress = "Last month"
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 24.dp),
            thickness = 1.dp,
            color = Color(0xFFC060B1)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 16.dp, end = 16.dp),
            text = stringResource(id = R.string.no_progress_text),
            fontSize = 14.sp,
            lineHeight = 14.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF531549)
        )

        Button(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
                .align(Alignment.Start),
            onClick = { actions.goToFavourite() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF531549)
            )
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 4.dp),
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "search"
            )
            Text(text = "Go to Favourite")
        }

        Button(
            modifier = Modifier
                .padding(top = 4.dp, end = 16.dp)
                .align(Alignment.End)
                .border(width = 1.dp, color = Color(0xFF531549), shape = RoundedCornerShape(24.dp)),
            onClick = { actions.findACourse() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0x00531549),
                contentColor = Color(0xFF531549)
            )
        ) {
            Text(text = "Search A Course")
            Icon(
                modifier = Modifier
                    .padding(start = 4.dp),
                imageVector = Icons.Outlined.Search,
                contentDescription = "search"
            )
        }

        Divider(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 24.dp),
            thickness = 1.dp,
            color = Color(0xFFC060B1)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Log Out",
                fontFamily = FontFamily.Serif,
                color = Color(0xFF531549),
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.weight(1f),
                text = "Remove Account",
                fontFamily = FontFamily.Serif,
                color = Color(0xFF531549),
                textAlign = TextAlign.Center
            )
        }

    }

}

@Composable
fun PeriodProgress(modifier: Modifier, progress: Float, textProgress: String) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = textProgress,
            fontFamily = FontFamily.Serif,
            color = Color(0xFF531549)
        )
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(90.dp),
            progress = progress,
            color = Color(0xFFC060B1)
        )
    }
}

@Preview
@Composable
fun Preview() {
    MyAccountScreen(actions = MyAccountActions({}, {}, {}, {}), currentUser = null)
}