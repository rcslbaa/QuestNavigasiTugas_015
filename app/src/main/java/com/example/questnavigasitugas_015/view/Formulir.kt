package com.example.questnavigasitugas_015.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulir(
    dataList: MutableList<List<String>>,
    OnSubmitBtnClick: () -> Unit,
    OnBackBtnClick: () -> Unit = {}
) {
// ===== Variabel input =====
    var namaKucing by remember { mutableStateOf("") }
    var jenisKelamin by remember { mutableStateOf("") }
    var jenisLayanan by remember { mutableStateOf("") }
    var namaPemilik by remember { mutableStateOf("") }

    val jenisKelaminList = listOf("Jantan", "Betina")
    val layananList = listOf(
        "Grooming Biasa",
        "Grooming Premium",
        "Mandi Anti Kutu",
        "Cukur Bulu",
        "Potong Kuku"
    )

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showWarning by remember { mutableStateOf(false) }

    //Tampilan utama
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFDBEBFF)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Formulir Pendaftaran Grooming",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E)
            )

            Spacer(modifier = Modifier.height(24.dp))

            //Card isi form
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Nama Kucing
                    Text("NAMA KUCING", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = namaKucing,
                        onValueChange = { namaKucing = it },
                        placeholder = { Text("Masukkan nama kucing") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Jenis Kelamin
                    Text("JENIS KELAMIN", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        jenisKelaminList.forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = jenisKelamin == item,
                                    onClick = { jenisKelamin = item }
                                )
                                Text(item)
                            }
                        }
                    }

                    // Jenis Layanan Grooming
                    Text("JENIS LAYANAN GROOMING", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = jenisLayanan,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("Pilih layanan grooming") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            layananList.forEach { layanan ->
                                DropdownMenuItem(
                                    text = { Text(layanan) },
                                    onClick = {
                                        jenisLayanan = layanan
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Nama Pemilik
                    Text("NAMA PEMILIK", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    OutlinedTextField(
                        value = namaPemilik,
                        onValueChange = { namaPemilik = it },
                        placeholder = { Text("Masukkan nama pemilik") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol Aksi
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = OnBackBtnClick,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Kembali")
                        }

                        Button(
                            onClick = {
                                if (namaKucing.isBlank() || jenisKelamin.isBlank() || jenisLayanan.isBlank() || namaPemilik.isBlank()) {
                                    showWarning = true
                                } else {
                                    // Simpan ke dataList
                                    dataList.add(listOf(namaKucing, jenisKelamin, jenisLayanan, namaPemilik))
                                    showDialog = true
                                }
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF80A4FF))
                        ) {
                            Text("Submit", color = Color.White)
                        }
                    }
                }
            }
        }

        //Dialog sukses
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                containerColor = Color(0xFF80A4FF),
                title = {
                    Text(
                        text = "Data Berhasil Dikirim",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column {
                        Text("Nama Kucing: $namaKucing", color = Color.White)
                        Text("Jenis Kelamin: $jenisKelamin", color = Color.White)
                        Text("Layanan: $jenisLayanan", color = Color.White)
                        Text("Pemilik: $namaPemilik", color = Color.White)
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            OnSubmitBtnClick()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Text("OK", color = Color(0xFF80A4FF))
                    }
                }
            )
        }

        //Dialog warning
        if (showWarning) {
            AlertDialog(
                onDismissRequest = { showWarning = false },
                title = { Text("Peringatan") },
                text = { Text("Harap isi semua data sebelum submit!") },
                confirmButton = {
                    Button(onClick = { showWarning = false }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}