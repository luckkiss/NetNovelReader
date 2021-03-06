package com.netnovelreader.data.local.db

import android.arch.persistence.room.*

@Dao
interface SitePreferenceDao {
    @Query("SELECT * FROM ${ReaderDatabase.TABLE_SP}")
    fun getAll(): List<SitePreferenceBean>

    @Query("SELECT * FROM ${ReaderDatabase.TABLE_SP} WHERE ${ReaderDatabase.HOSTNAME} LIKE :hostname")
    fun getRule(hostname: String): SitePreferenceBean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg beans: SitePreferenceBean)

    @Delete
    fun delete(bean: SitePreferenceBean)
}