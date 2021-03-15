package com.sn.cleannewsapp.data.cache

import androidx.room.*
import com.sn.cleannewsapp.data.entities.Article
import com.sn.cleannewsapp.utils.Constant
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article): Flow<Long>

    @Query("SELECT * FROM " + Constant.ARTICLE_TABLE_NAME)
    fun getAllArticle(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)*/
}