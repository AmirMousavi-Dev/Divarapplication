package com.amirmousavi.core.di

import android.content.Context
import androidx.room.Room
import com.amirmousavi.core.data.database.CityDao
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.data.database.PostDao
import com.amirmousavi.core.data.database.PostViewDao
import com.amirmousavi.core.data.datastore.DivarDatastoreImpl
import com.amirmousavi.core.domain.datastore.DivarDataStore
import com.amirmousavi.core.util.Constants.BASE_URL
import com.amirmousavi.core.util.DivarInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        val divarInterceptor = DivarInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient()
            .newBuilder()
            .apply {
                addInterceptor(logging)
                addInterceptor(divarInterceptor)
                readTimeout(30, TimeUnit.SECONDS)
                connectTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
            }
            .build()
        return client
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
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
    )
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideCityDao(
        database: DivarDatabase
    ): CityDao =
        database.cityDao()

    @Provides
    @Singleton
    fun providePostDao(
        database: DivarDatabase
    ): PostDao =
        database.postDao()


    @Provides
    @Singleton
    fun providePostViewDao(
        database: DivarDatabase
    ): PostViewDao =
        database.postViewDao()


}
