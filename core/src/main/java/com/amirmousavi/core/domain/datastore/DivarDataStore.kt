package com.amirmousavi.core.domain.datastore


interface DivarDataStore {

    suspend fun saveCityId(id: Int)
    suspend fun getCityId(): Int

    suspend fun saveCityFaName(name: String)
    suspend fun getCityFaName(): String?


    suspend fun saveShouldShowOnboarding(value: Boolean)
    fun getShouldShowOnboarding(): Boolean

}