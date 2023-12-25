package com.example.luck

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class PyschTest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent() { face() }
    }


    @Composable
    fun face() {
        Column {
            Text(
                text = "心理测试",
                fontSize = 30.sp,
                fontFamily = FontFamily(Font(resId = R.font.opposans_b)),
                modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 30.dp)
            )
            LazyColumn {
                item {
                    TestsList(
                        title = stringResource(R.string.title_mbti),
                        des = stringResource(R.string.des_mbti),
                        url = stringResource(R.string.url_mbti)
                    )
                    TestsList(
                        title = stringResource(R.string.title_depression),
                        des = stringResource(R.string.des_depression),
                        url = stringResource(R.string.url_depression)
                    )
                    TestsList(
                        title = stringResource(R.string.title_love),
                        des = stringResource(R.string.des_love),
                        url = stringResource(R.string.url_love)
                    )
                    TestsList(
                        title = stringResource(R.string.title_crush),
                        des = stringResource(R.string.des_crush),
                        url = stringResource(R.string.url_crush)
                    )
                    TestsList(
                        title = stringResource(R.string.title_sex),
                        des = stringResource(R.string.des_sex),
                        url = stringResource(R.string.url_sex)
                    )
                    TestsList(
                        title = stringResource(R.string.title_waifu),
                        des = stringResource(R.string.des_waifu),
                        url = stringResource(R.string.url_waifu)
                    )
                }
            }
        }
    }

    @Composable
    fun TestsList(title: String, des: String, url: String) {
        Box(
            modifier = Modifier.padding(start = 20.dp, bottom = 15.dp, end = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color(0xFFFFD9D5))
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);7
                        intent.setData(Uri.parse(url))
                        startActivity(intent)
                    }
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(resId = R.font.opposans_m)),
                    modifier = Modifier.padding(start = 15.dp, top = 15.dp, end = 15.dp)
                )
                Text(
                    text = des,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(resId = R.font.opposans_l)),
                    modifier = Modifier.padding(start = 15.dp, bottom = 15.dp, end = 15.dp)
                )
            }
        }
    }
}