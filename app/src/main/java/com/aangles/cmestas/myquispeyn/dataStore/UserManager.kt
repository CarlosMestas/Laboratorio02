package com.aangles.cmestas.myquispeyn.dataStore

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.aangles.cmestas.myquispeyn.screens.userManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "user_prefs")

    companion object {
        val NICKNAME_KEY :Preferences.Key<String> = preferencesKey<String>("NICKNAME")
        val GENDER_KEY :Preferences.Key<Boolean> = preferencesKey<Boolean>("GENDER")
        val QUANTITYOFINTERACTIONSREGIONS_KEY :Preferences.Key<Int> = preferencesKey<Int>("QUANTITYOFINTERACTIONSREGIONS_KEY")
        val QUANTITYOFINTERACTIONSGPS_KEY :Preferences.Key<Int> = preferencesKey<Int>("QUANTITYOFINTERACTIONSGPS")
    }

    suspend fun storeNickName(nickname: String){
        dataStore.edit {
            it[NICKNAME_KEY] = nickname
        }
    }
    suspend fun storeQuantityOfInteractionsRegions(number: Int) {
        dataStore.edit {
            it[QUANTITYOFINTERACTIONSREGIONS_KEY] = number
        }
    }
    suspend fun storeQuantityOfInteractionsGps(number: Int) {
        dataStore.edit {
            it[QUANTITYOFINTERACTIONSGPS_KEY] = number
        }
    }

    suspend fun storeGender(gender: Boolean) {
        dataStore.edit {
            it[GENDER_KEY] = gender
        }
    }

    val nicknameFlow: Flow<String> = dataStore.data.map {
        it[NICKNAME_KEY] ?: ""
    }

    val quantityofinteractionsregions: Flow<Int> = dataStore.data.map {
        it[QUANTITYOFINTERACTIONSREGIONS_KEY] ?: 0
    }

    val genderFlow: Flow<Boolean> = dataStore.data.map {
        it[GENDER_KEY] ?: true
    }

    val quantityofinteractionsgps: Flow<Int> = dataStore.data.map {
        it[QUANTITYOFINTERACTIONSGPS_KEY] ?: 0
    }


}