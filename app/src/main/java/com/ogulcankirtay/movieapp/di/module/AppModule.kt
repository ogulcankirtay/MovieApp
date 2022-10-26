package com.ogulcankirtay.movieapp.di.module

import android.app.Application
import android.content.Context
import com.ogulcankirtay.movieapp.di.dao.GenreDao
import com.ogulcankirtay.movieapp.di.dao.GenreDb
import com.ogulcankirtay.movieapp.di.retrofit.RetrofitServiceInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    var baseUrl="https://api.themoviedb.org/"

    @Provides
    @Singleton
    fun getDb(context: Application): GenreDb{

        return GenreDb.getAppdb(context)
    }

    @Provides
    @Singleton
    fun getDao(appDb: GenreDb): GenreDao{
        return appDb.getDao()
    }

    @Provides
    @Singleton
    fun getRetrofitServiceInstance(retrofit: Retrofit): RetrofitServiceInstance
    {
        return retrofit.create(RetrofitServiceInstance::class.java)
    }
    @Provides
    @Singleton
    fun getRetroInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}