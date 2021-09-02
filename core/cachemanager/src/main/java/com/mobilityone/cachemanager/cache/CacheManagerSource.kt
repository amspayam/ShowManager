package com.combyne.cachemanager.cache

/**
 * Created by Rasoul Miri on 06/08/2021
 */
interface CacheManagerSource {
    fun save(key: String, value: Any?): Boolean
    fun load(key: String, type: Any)
}
