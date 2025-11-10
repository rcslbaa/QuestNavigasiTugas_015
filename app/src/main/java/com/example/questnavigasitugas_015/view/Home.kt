package com.example.questnavigasiui_015.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.questnavigasitugas_015.R

@Composable
fun Home(
    OnMasukBtnClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xAADBEBFF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            //Teks Selamat Datang
            Text(
                text = "SELAMAT DATANG",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3737A6),
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            //Logo
            Image(
                painter = painterResource(id = R.drawable.logo), // pastikan logo ada di res/drawable
                contentDescription = "Logo CatGroom",
                modifier = Modifier
                    .size(250.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Teks di bawah logo
            Text(
                text = "\uD83D\uDC3E CatGroom",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF3737A6),
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )

            Text(
                text = "SALON KUCINGMU",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF3737A6),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(145.dp))

