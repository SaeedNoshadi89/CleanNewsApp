package com.sn.cleannewsapp.data.cache

import androidx.room.*
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM " + Constant.ARTICLE_TABLE_NAME)
    fun getAllArticle(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article): Long

    @Delete
    suspend fun deleteArticle(article: Article)
}