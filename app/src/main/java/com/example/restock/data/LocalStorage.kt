package com.example.restock.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Nombre del archivo de DataStore
private const val DS_NAME = "restock_prefs"

// Extension para acceder a DataStore desde cualquier Context
val Context.dataStore by preferencesDataStore(name = DS_NAME)


object LocalStorage {
    // Keys
    private val KEY_NAME   = stringPreferencesKey("user_name")
    private val KEY_EMAIL  = stringPreferencesKey("user_email")
    private val KEY_PASS   = stringPreferencesKey("user_pass")
    private val KEY_LOGGED = booleanPreferencesKey("is_logged")

    // Escrituras

    /** Guarda/actualiza usuario */
    suspend fun saveUser(ctx: Context, name: String, email: String, pass: String) {
        ctx.dataStore.edit { p ->
            p[KEY_NAME]  = name
            p[KEY_EMAIL] = email
            p[KEY_PASS]  = pass
        }
    }

    /** Marca sesión iniciada o cerrada */
    suspend fun setLogged(ctx: Context, logged: Boolean) {
        ctx.dataStore.edit { p ->
            p[KEY_LOGGED] = logged
        }
    }

    /** Limpia sesión (no borra el usuario guardado) */
    suspend fun logout(ctx: Context) {
        setLogged(ctx, false)
    }

    // Lecturas

    /** Verifica credenciales contra lo guardado */
    suspend fun checkLogin(ctx: Context, email: String, pass: String): Boolean {
        val p = ctx.dataStore.data.first()
        val savedEmail = p[KEY_EMAIL] ?: ""
        val savedPass  = p[KEY_PASS]  ?: ""
        val ok = (email == savedEmail && pass == savedPass)
        if (ok) setLogged(ctx, true)
        return ok
    }

    /** Obtiene el nombre del usuario (para saludar en Home, por ejemplo) */
    suspend fun getUserName(ctx: Context): String? {
        val p = ctx.dataStore.data.first()
        return p[KEY_NAME]
    }

    /** Indica si hay sesión activa (útil para un SplashScreen) */
    suspend fun isLogged(ctx: Context): Boolean {
        val p = ctx.dataStore.data.first()
        return p[KEY_LOGGED] ?: false
    }
}
