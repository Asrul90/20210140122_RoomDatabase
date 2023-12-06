package com.example.roomsiswa.repositori

import android.content.Context
import com.example.roomsiswa.data.DataBaseSiswa

interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OflineRepositoriSiswa(DataBaseSiswa.getDatabase(context).siswaDao())
    }

}