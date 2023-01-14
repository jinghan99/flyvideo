package top.jinghan94.flyvideo.logic.dao

import androidx.room.Dao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
import top.jinghan94.flyvideo.logic.model.VideoListModel

@Dao
interface VideoDao {

    @GET("https://cj.lziapi.com/api.php/provide/vod/?ac=list")
    fun queryList(): Call<VideoListModel>


    @GET("/api.php/provide/vod/?ac=list")
    fun queryList(@Query("wd") wd: String, @Query("pg") pg: String): Call<VideoListModel>

    @GET("/api.php/provide/vod/?ac=detail")
    fun queryListDetail(@Query("wd") wd: String, @Query("pg") pg: String): Call<VideoListModel>

    //    当使用了@URL时，会默认使用传进来的URL的参数，
    //    这个特殊的用法可以支持URL是动态变化的情况，并且解决了Base URL必须以／结尾的规定
    @GET
    fun queryUrl(@Url url: String): Call<VideoListModel>
}