package heven.holt.jetpack.github.api

import androidx.lifecycle.LiveData
import heven.holt.jetpack.github.vo.Contributor
import heven.holt.jetpack.github.vo.Repo
import heven.holt.jetpack.github.vo.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
interface GithubService {
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String): RepoSearchResponse

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RepoSearchResponse>
}