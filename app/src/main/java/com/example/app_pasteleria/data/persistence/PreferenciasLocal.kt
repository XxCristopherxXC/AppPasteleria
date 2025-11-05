package com.milsabores.pasteleria.data.persistence

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

// 🔹 Extensión de contexto para DataStore
private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object PreferenciasLocal {

    private val NOMBRE_KEY = stringPreferencesKey("nombre_usuario")

    // Guarda el nombre del usuario
    fun guardarNombre(context: Context, nombre: String) = runBlocking {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE_KEY] = nombre
        }
    }

    // Obtiene el nombre del usuario
    fun obtenerNombre(context: Context): String? = runBlocking {
        context.dataStore.data.first()[NOMBRE_KEY]
    }
}

