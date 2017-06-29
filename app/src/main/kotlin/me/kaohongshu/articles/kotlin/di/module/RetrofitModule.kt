package me.kaohongshu.articles.kotlin.di.module

import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import me.kaohongshu.articles.kotlin.di.qualifier.ForApp
import me.kaohongshu.articles.kotlin.util.NetUtils
import okhttp3.*
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Author: shichunxiang
 * Date: 2017/6/28 0028
 * Des:
 */
@Module
class RetrofitModule {
    @Provides @Singleton
    fun provideRetrofit(@Named("baseUrl") baseUrl: String, client: OkHttpClient,
                        converter: GsonConverterFactory,
                        adapter: RxJava2CallAdapterFactory): Retrofit {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(converter)
                .addCallAdapterFactory(adapter)
                .build()
        return retrofit
    }

    @Provides @Named("baseUrl")
    fun provideBaseUrl() = "http://101.200.34.13:8080/tuicool/"

    @Provides @Singleton
    fun provideOkHttpClient(@ForApp context: Context,
                            @Named("log") log: Interceptor,
                            @Named("cache_control") cacheControl: Interceptor): OkHttpClient {
        val timeOut = 60 * 3L
        val builder = OkHttpClient.Builder()
                .connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
        //缓存设置
        val cacheFile = File(context.cacheDir, "okhttp")
        val cache = Cache(cacheFile, 1024 * 1024 * 10)
        builder.cache(cache)
                .addInterceptor(log)
                .addInterceptor(cacheControl)
        return builder.build()
    }

    @Provides
    fun provideConverterFactory() = GsonConverterFactory.create()

    @Provides
    fun provideCallAdapter() = RxJava2CallAdapterFactory.create()

    @Provides @Named("log")
    fun provideLogInterceptor() = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            var request = chain?.request()
            Log.e("okhttp", request.toString())
            var response = chain?.proceed(request)!!
            if (response.isSuccessful) {
                val contentType = response.body()?.contentType()
                val body = response.body()?.string()
                Log.e("okhttp", body)
                response = response.newBuilder()
                        .body(ResponseBody.create(contentType, body))
                        .build()
            } else {
                Log.e("okhttp", response.message())
            }
            return response
        }
    }

    @Provides @Named("cache_control")
    fun provideCacheControlInterceptor() = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            var request = chain?.request()
            if (!NetUtils.isNetworkConnected()) {
                request = request?.newBuilder()
                        ?.cacheControl(CacheControl.FORCE_CACHE)
                        ?.build()
            }
            var response = chain?.proceed(request)
            if (NetUtils.isNetworkConnected()) {
                val maxAge = 60 * 60
                response = response?.newBuilder()
                        ?.removeHeader("Pragma")
                        ?.addHeader("Cache-Control", "public, max-age=${maxAge}")
                        ?.build()
            } else {
                val maxStale = 60 * 60 * 24 * 28
                response = response?.newBuilder()
                        ?.removeHeader("Pragma")
                        ?.addHeader("Cache-Control", "public, only-if-cached, max-stale=${maxStale}")
                        ?.build()
            }
            return response!!
        }
    }
}