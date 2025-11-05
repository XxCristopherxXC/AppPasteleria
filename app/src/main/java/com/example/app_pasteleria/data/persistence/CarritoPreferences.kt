package com.milsabores.pasteleria.data.persistence

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.milsabores.pasteleria.data.models.ItemCarrito
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión de Context para DataStore
val Context.carritoDataStore by preferencesDataStore(name = "carrito_prefs")

object CarritoPreferences {

    private val CARRITO_KEY = stringPreferencesKey("carrito_items")

    // Guardar lista de items en DataStore como JSON
    suspend fun guardarCarrito(context: Context, items: List<ItemCarrito>) {
        val json = Gson().toJson(items)
        context.carritoDataStore.edit { prefs ->
            prefs[CARRITO_KEY] = json
        }
    }

    // Recuperar lista de items desde DataStore
    fun obtenerCarrito(context: Context): Flow<List<ItemCarrito>> {
        return context.carritoDataStore.data.map { prefs ->
            val json = prefs[CARRITO_KEY] ?: "[]"
            val type = object : TypeToken<List<ItemCarrito>>() {}.type
            Gson().fromJson(json, type)
        }
    }
}
