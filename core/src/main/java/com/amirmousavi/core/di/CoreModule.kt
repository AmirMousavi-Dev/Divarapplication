package com.amirmousavi.core.di

import android.content.Context
import androidx.room.Room
import com.amirmousavi.core.data.database.CityDao
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.data.datastore.DivarDatastoreImpl
import com.amirmousavi.core.domain.datastore.DivarDataStore
import com.amirmousavi.core.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
    ): DivarDataStore =
        DivarDatastoreImpl(context)


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DivarDatabase = Room.databaseBuilder(
        context,
        DivarDatabase::class.java,
        "DivarDatabase"
    ).build()


    @Provides
    fun provideItemDao(
        database: DivarDatabase
    ): CityDao =
        database.cityDao()


}
