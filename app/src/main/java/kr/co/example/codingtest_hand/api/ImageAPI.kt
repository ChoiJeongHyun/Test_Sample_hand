package kr.co.example.codingtest_hand.api


import io.reactivex.rxjava3.core.Single
import kr.co.example.codingtest_hand.vo.Body
import retrofit2.Call
import retrofit2.http.*

interface ImageAPI {


    @GET("/v2/search/image")
    fun getImgRx(@Query("query") query: String , @Query("sort") sort:String = "accuracy" , @Query("page") page:Int , @Query("size") size:Int ): Single<Body>

    @GET("/v2/search/image")
    fun getImg(@Query("query") query: String , @Query("sort") sort:String = "accuracy" , @Query("page") page:Int , @Query("size") size:Int ): Call<Body>



}