package kr.co.example.codingtest_hand.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import kr.co.example.codingtest_hand.di.ApplicationContext
import kr.co.example.codingtest_hand.repository.AppRepository
import kr.co.example.codingtest_hand.api.ApiManager
import kr.co.example.codingtest_hand.database.AppDatabase
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(application: Application) = application

    @Provides
    @Singleton
    fun provideAppRepository(apiManager: ApiManager, appDatabase: AppDatabase) = AppRepository(apiManager, appDatabase)

    @Provides
    @Singleton
    fun provideApiManager() = ApiManager()

    @Provides
    @Singleton
    fun provideAppDataBase() = AppDatabase.getInstance()

}