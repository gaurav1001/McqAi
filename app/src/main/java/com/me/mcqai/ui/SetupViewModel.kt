package com.me.mcqai.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.me.mcqai.data.datasource.PrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import guru.mcqai.www.SETUP_COMPLETED
import guru.mcqai.www.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SetupViewModel @Inject constructor ( private val prefManager: PrefManager) : ViewModel() {

    val isSetupCompleted: StateFlow<Boolean?> = prefManager.settingData.
        map {
            it.isSetupCompleted
        }.
    stateIn(
                scope = viewModelScope,
                initialValue = null,
                started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000)
            )

    fun updateSetup(
        course: String,
        level: String
    ){
        viewModelScope.launch() {
            prefManager.updateSetup(course, level)
        }

    }
    
}