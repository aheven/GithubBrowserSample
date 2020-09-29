package heven.holt.jetpack.github.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import heven.holt.jetpack.github.api.GithubService
import heven.holt.jetpack.github.db.GithubDb
import heven.holt.jetpack.github.db.RepoDao
import heven.holt.jetpack.github.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *Time:2020/9/27
 *Author:HevenHolt
 *Description:
 */
@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }).build()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideGithubDb(@ApplicationContext context: Context): GithubDb {
        return Room.databaseBuilder(context, GithubDb::class.java, "githubDb").build()
    }

    @Singleton
    @Provides
    fun provideRepoDao(githubDb: GithubDb): RepoDao {
        return githubDb.repoDao()
    }
}