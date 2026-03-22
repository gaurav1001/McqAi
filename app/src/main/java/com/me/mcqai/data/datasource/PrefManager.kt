package com.me.mcqai.data.datasource

import android.util.Log
import androidx.datastore.core.DataStore
import guru.mcqai.www.Settings
import guru.mcqai.www.copy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefManager @Inject constructor(
   private val dataStore: DataStore<Settings>
) {

    val settingData: Flow<Settings> = dataStore.data

    suspend fun updateSetup(courseName: String, levelName: String) {
        try {
            dataStore.updateData { currentSettings ->
                currentSettings.copy {
                    course = courseName
                    level = levelName
                    isSetupCompleted = true
                }
            }
        } catch (e: Exception) {
            Log.e("PrefManager", "Error updating settings", e)
        }
    }
}
