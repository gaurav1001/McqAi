package com.me.mcqai.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import guru.mcqai.www.SETUP_COMPLETED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SetupViewModel @Inject constructor ( private val dataStore: DataStore<Preferences>) : ViewModel() {
    val isSetupCompleted: Flow<Boolean> = dataStore.data.catch { exception ->
        if(exception is IOException){
            throw exception
        }else{
            throw exception
        }
    }.map { preferences ->
        preferences[SETUP_COMPLETED] ?: false

    }

    fun updateSetupCompleted(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.edit { setup ->
                setup[SETUP_COMPLETED] = completed
            }
        }
    }


}