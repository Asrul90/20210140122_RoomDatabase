package com.example.roomsiswa.repositori

import com.example.roomsiswa.data.Siswa
import com.example.roomsiswa.data.SiswaDao
import kotlinx.coroutines.flow.Flow

class OflineRepositoriSiswa(private val siswaDao: SiswaDao) : RepositoriSiswa{
    override fun getAllSiswastream(): Flow<List<Siswa>> = siswaDao.getALLSiswa()

    override fun getSiswastream(id: Int): Flow<Siswa?>  {
       return siswaDao.getSiswa(id)
    }
    override suspend fun insertSiswa(siswa: Siswa) = siswaDao.insert(siswa)

    override suspend fun deleteSiswa(siswa: Siswa) = siswaDao.delete(siswa)

    override suspend fun updateSiswa(siswa: Siswa) {siswaDao.update(siswa)}
}