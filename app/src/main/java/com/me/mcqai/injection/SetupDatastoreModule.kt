package com.me.mcqai.injection

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import guru.mcqai.www.setupDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SetupDatastoreModule {
    @Provides
    fun provideSetUpDataStore(
        application: Application
    ): DataStore<Preferences> {
        return application.setupDataStore
    }

}