package com.combyne.cache

interface CacheManagerSource {
    fun save(key: String, value: Any?): Boolean
    fun load(key: String, type: Any)
}
