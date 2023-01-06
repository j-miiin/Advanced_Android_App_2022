package com.example.delivery_service_part5_chapter06.data.repository

import com.example.delivery_service_part5_chapter06.data.api.SweetTrackerApi
import com.example.delivery_service_part5_chapter06.data.db.ShippingCompanyDao
import com.example.delivery_service_part5_chapter06.data.entity.ShippingCompany
import com.example.delivery_service_part5_chapter06.data.preference.PreferenceManager
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ShippingCompanyRepositoryImpl(
    private val trackerApi: SweetTrackerApi,
    private val shippingCompanyDao: ShippingCompanyDao,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : ShippingCompanyRepository {

    override suspend fun getShippingCompanies(): List<ShippingCompany> = withContext(dispatcher) {
        val currentTimeMillis = System.currentTimeMillis()
        val lastDatabaseUpdatedTimeMillis = preferenceManager.getLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS)

        if (lastDatabaseUpdatedTimeMillis == null ||
                CACHE_MAX_AGE_MILLIS < (currentTimeMillis - lastDatabaseUpdatedTimeMillis)) {
                    val shippingCompanies = trackerApi.getShippingCompanies()
                        .body()
                        ?.shippingCompanies
                        ?: emptyList()
                shippingCompanyDao.insert(shippingCompanies)
                preferenceManager.putLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS, currentTimeMillis)
        }
        shippingCompanyDao.getAll()
    }

    companion object {
        private const val KEY_LAST_DATABASE_UPDATED_TIME_MILLIS = "KEY_LAST_DATABASE_UPDATED_TIME_MILLIS"
        private const val CACHE_MAX_AGE_MILLIS = 1000L * 60 * 60 * 24 * 7
    }
}