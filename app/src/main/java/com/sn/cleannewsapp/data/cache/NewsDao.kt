package com.sn.cleannewsapp.data.cache

import androidx.room.Dao

@Dao
interface NewsDao {

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article): Flow<Long>

    @Query("SELECT * FROM " + Constant.ARTICLE_TABLE_NAME)
    fun getAllArticle(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)*/
}