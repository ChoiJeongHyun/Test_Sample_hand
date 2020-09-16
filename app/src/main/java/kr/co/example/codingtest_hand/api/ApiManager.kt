package kr.co.example.codingtest_hand.api

import kr.co.example.codingtest_hand.utils.C
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Singleton
class ApiManager {

    private val TIMEOUT = 30L

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient().newBuilder().apply {
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(httpLoggingInterceptor)
        addInterceptor(HeaderInterceptor(C.API_KEY))
    }.build()

    private var retrofit = Retrofit.Builder()
        .baseUrl(C.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
        .build()

    internal fun <T> getRetrofitService(apiClass: Class<T>) = retrofit.create(apiClass)


    private inner class HeaderInterceptor constructor(private val token: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request =
                chain.request().newBuilder().addHeader("Authorization", "KakaoAK $token").build()
            return chain.proceed(request)
        }
    }

}