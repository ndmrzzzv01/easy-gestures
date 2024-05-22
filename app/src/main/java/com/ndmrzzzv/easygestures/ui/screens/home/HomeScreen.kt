package com.ndmrzzzv.easygestures.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ndmrzzzv.easygestures.R
import com.ndmrzzzv.easygestures.data.HomeData
import com.ndmrzzzv.easygestures.data.HomeList
import com.ndmrzzzv.easygestures.data.HomeTypeScreen

@Composable
fun HomeScreen(actions: HomeScreenActions) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 11f),
            painter = painterResource(id = R.drawable.home_image_welcome),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 24.dp)
        ) {
            items(HomeList.get()) {
                ItemPage(pageItem = it, actions)
            }
        }
    }
}

@Composable
fun ItemPage(pageItem: HomeData, actions: HomeScreenActions) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                when (pageItem.type) {
                    HomeTypeScreen.MY_ACCOUNT -> {
                        actions.goToMyAccountPage()
                    }

                    HomeTypeScreen.SEARCH -> {
                        actions.goToSearchPage()
                    }

                    HomeTypeScreen.FAVOURITE -> {
                        actions.goToFavouritePage()
                    }

                }
            },
        border = BorderStroke(1.dp, Color.Magenta),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        if (pageItem.id % 2 == 0) {
            EvenItemPage(pageItem)
        } else {
            OddItemPage(pageItem)
        }
    }
}

@Composable
fun EvenItemPage(pageItem: HomeData) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier
                .padding(24.dp),
            painter = painterResource(id = pageItem.image),
            contentDescription = ""
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 16.dp),
        ) {
            Text(
                modifier = Modifier,
                text = pageItem.title,
                fontSize = 22.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                modifier = Modifier.padding(end = 16.dp, top = 8.dp),
                text = pageItem.description,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                lineHeight = 14.sp,
            )
        }
    }
}

@Composable
fun OddItemPage(pageItem: HomeData) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 24.dp, start = 24.dp, bottom = 24.dp)
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier,
                text = pageItem.title,
                fontSize = 22.sp,
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.End
            )
            Text(
                modifier = Modifier.padding(top = 2.dp),
                text = pageItem.description,
                fontSize = 12.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.End,
                lineHeight = 14.sp,
            )
        }

        Image(
            modifier = Modifier
                .padding(24.dp),
            painter = painterResource(id = pageItem.image),
            contentDescription = ""
        )

    }
}


//@Preview
//@Composable
//fun Preview() {
//    ItemPage(id = 1)
//}

