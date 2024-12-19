package com.amirmousavi.post_data.di

import com.amirmousavi.core.data.database.CityDao
import com.amirmousavi.core.data.database.DivarDatabase
import com.amirmousavi.core.data.database.PostDao
import com.amirmousavi.core.data.database.PostViewDao
import com.amirmousavi.core.domain.datastore.DivarDataStore
import com.amirmousavi.post_data.remote.CityApiService
import com.amirmousavi.post_data.remote.PostApiService
import com.amirmousavi.post_data.repository.CityRepositoryImpl
import com.amirmousavi.post_data.repository.PostRepositoryImpl
import com.amirmousavi.post_domain.repository.CityRepository
import com.amirmousavi.post_domain.repository.PostRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostDataModule {


    @Singleton
    @Provides
    fun provideCityApiService(
        retrofit: Retrofit
    ): CityApiService = retrofit.create(CityApiService::class.java)


    @Singleton
    @Provides
    fun provideCityRepository(
        dao: CityDao,
        apiService: CityApiService
    ): CityRepository = CityRepositoryImpl(
        dao = dao,
        apiService = apiService
    )


    @Singleton
    @Provides
    fun providePostApiService(
        retrofit: Retrofit
    ): PostApiService = retrofit.create(PostApiService::class.java)


    @Singleton
    @Provides
    fun providePostRepository(
        database: DivarDatabase,
        dataStore: DivarDataStore,
        postDao: PostDao,
        postViewDao: PostViewDao,
        apiService: PostApiService,
        gson: Gson
    ): PostRepository = PostRepositoryImpl(
        postDao = postDao,
        postDetailDao = postViewDao,
        apiService = apiService,
        database = database,
        dataStore = dataStore,
        gson = gson
    )

    @Singleton
    @Provides
    fun provideGson() :Gson = Gson()

}

