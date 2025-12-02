package com.milsabores.pasteleria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milsabores.pasteleria.data.remote.PromoApiItem
import com.milsabores.pasteleria.data.remote.PromocionesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PromocionesViewModel : ViewModel() {

    private val repo = PromocionesRepository()

    private val _promosApi = MutableStateFlow<List<PromoApiItem>>(emptyList())
    val promosApi: StateFlow<List<PromoApiItem>> = _promosApi

    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando

    init {
        cargarPromos()
    }

    fun cargarPromos() {
        viewModelScope.launch {
            _cargando.value = true
            try {
                _promosApi.value = repo.cargarPromosApi()
            } catch (e: Exception) {
                _promosApi.value = emptyList()
            }
            _cargando.value = false
        }
    }
}

