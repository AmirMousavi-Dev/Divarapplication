package com.amirmousavi.post_data.di

import com.amirmousavi.core.data.database.CityDao
import com.amirmousavi.post_data.remote.CityApiService
import com.amirmousavi.post_data.repository.CityRepositoryImpl
import com.amirmousavi.post_domain.repository.CityRepository
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
    fun provideCityRepository(
        dao: CityDao,
        apiService: CityApiService
    ): CityRepository =
        CityRepositoryImpl(
            dao = dao,
            apiService = apiService
        )


    @Singleton
    @Provides
    fun provideCityApiService(
        retrofit: Retrofit
    ): CityApiService = retrofit.create(CityApiService::class.java)


}

