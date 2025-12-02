package com.milsabores.pasteleria.data.persistence

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore("usuario_prefs")

object PreferenciasLocal {

    private val CLAVE_NOMBRE = stringPreferencesKey("usuario_nombre")
    private val CLAVE_APELLIDO = stringPreferencesKey("usuario_apellido")
    private val CLAVE_CORREO = stringPreferencesKey("usuario_correo")

    suspend fun guardarNombre(context: Context, nombre: String) {
        context.dataStore.edit { prefs ->
            prefs[CLAVE_NOMBRE] = nombre
        }
    }

    suspend fun guardarApellido(context: Context, apellido: String) {
        context.dataStore.edit { prefs ->
            prefs[CLAVE_APELLIDO] = apellido
        }
    }

    suspend fun guardarCorreo(context: Context, correo: String) {
        context.dataStore.edit { prefs ->
            prefs[CLAVE_CORREO] = correo
        }
    }

    fun obtenerNombre(context: Context) =
        context.dataStore.data.map { prefs -> prefs[CLAVE_NOMBRE] }

    fun obtenerApellido(context: Context) =
        context.dataStore.data.map { prefs -> prefs[CLAVE_APELLIDO] }

    fun obtenerCorreo(context: Context) =
        context.dataStore.data.map { prefs -> prefs[CLAVE_CORREO] }

    // 🔥 ESTA ES LA FUNCIÓN NUEVA
    suspend fun limpiarDatos(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(CLAVE_NOMBRE)
            prefs.remove(CLAVE_APELLIDO)
            prefs.remove(CLAVE_CORREO)
        }
    }
}




