package com.example.roomsiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.RepositoriSiswa
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoriSiswa
): ViewModel(){

    var siswaUiState by mutableStateOf(UIStateSiswa())
        private  set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.ItemIdArg])

    init {
        viewModelScope.launch {
            siswaUiState = repositoriSiswa.getSiswastream(itemId).filterNotNull().first().toUiStateSiswa(true)
        }
    }

    suspend fun updateSiswa(){
        if (validasiInput(siswaUiState.DetailSiswa)){
            repositoriSiswa.updateSiswa(siswaUiState.DetailSiswa.toSiswa())
        }
        else{
            println("Data Tidak Valid")
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa){
        siswaUiState =
            UIStateSiswa(DetailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }


    private fun validasiInput(uiState: DetailSiswa = siswaUiState.DetailSiswa): Boolean{
        return with(uiState){
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

}