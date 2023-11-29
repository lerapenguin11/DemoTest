package com.example.demotest.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class TokenViewModel(application: Application) : AndroidViewModel(application) {
    val codeSher : SharedPreferences = application.getSharedPreferences(PREF_PROFILE, Context.MODE_PRIVATE)

    fun encryptToken(context: Context, token: String?) {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
            ENCRYPTED_PREF,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun decryptToken(context: Context): String? {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
            ENCRYPTED_PREF,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        return sharedPreferences.getString(TOKEN, null)
    }

    fun deleteToken(context: Context) {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        val sharedPreferences = EncryptedSharedPreferences.create(
            ENCRYPTED_PREF,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        sharedPreferences.edit().remove(TOKEN).apply()
    }

    fun getAuth(code : Int) {
        codeSher.edit().apply {
            putInt(CODE, code)
            apply()
        }
    }

    companion object{
        private const val CODE = "code"
        private const val TOKEN = "token"
        private const val ENCRYPTED_PREF = "encrypted_prefs"
        private const val PREF_PROFILE = "pref_pofile"
    }
}