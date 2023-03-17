package com.herald.watchwise.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.herald.watchwise.common.Constants.Base_Url
import com.herald.watchwise.data.local.WatchLaterDatabase
import com.herald.watchwise.data.local.repository.WatchLaterImpl
import com.herald.watchwise.data.remote.RestApiService
import com.herald.watchwise.data.remote.repository.RestApiImpl
import com.herald.watchwise.domain.repository.RestApiRepo
import com.herald.watchwise.domain.repository.WatchLaterRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This class is a module that provides dependencies for the application using the Dagger Hilt dependency injection framework.
 * The class defines several functions using the @Provides annotation, which specifies that the function provides a dependency
 * for other parts of the application.
 * The @InstallIn annotation is used to specify the scope of the module, which in this case is the SingletonComponent.
 * This means that the dependencies provided by this module will have a singleton scope, which ensures that the same
 * instance of the dependency is used throughout the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRestApiService(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(restApiService: RestApiService): RestApiRepo {
        return RestApiImpl(restApiService)
    }

    @Provides
    @Singleton
    fun provideWatchLaterDatabase(app: Application): WatchLaterDatabase {
        return Room.databaseBuilder(
            app, WatchLaterDatabase::class.java, WatchLaterDatabase.DB_Name
        ).build()
    }

    @Singleton
    @Provides
    fun getWatchLaterRepo(db: WatchLaterDatabase): WatchLaterRepo {
        return WatchLaterImpl(db.watchLaterDao())
    }

    @Singleton
    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }
}