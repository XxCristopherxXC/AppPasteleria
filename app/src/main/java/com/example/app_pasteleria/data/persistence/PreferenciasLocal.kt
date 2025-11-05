package com.milsabores.pasteleria.data.persistence

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 🔹 Extensión de contexto para DataStore
val Context.dataStore by preferencesDataStore(name = "user_prefs")

object PreferenciasLocal {

    private val NOMBRE_KEY = stringPreferencesKey("nombre_usuario")

    // Guarda el nombre del usuario
    suspend fun guardarNombre(context: Context, nombre: String) {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE_KEY] = nombre
        }
    }

    // Obtiene el nombre del usuario como Flow
    fun obtenerNombre(context: Context): Flow<String?> {
        return context.dataStore.data
            .map { prefs ->
                prefs[NOMBRE_KEY]
            }
    }
}


