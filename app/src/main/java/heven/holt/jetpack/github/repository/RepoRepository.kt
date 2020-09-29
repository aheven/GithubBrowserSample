package heven.holt.jetpack.github.repository

import androidx.lifecycle.LiveData
import heven.holt.jetpack.github.api.ApiResponse
import heven.holt.jetpack.github.api.GithubService
import heven.holt.jetpack.github.api.RepoSearchResponse
import heven.holt.jetpack.github.db.GithubDb
import heven.holt.jetpack.github.db.RepoDao
import heven.holt.jetpack.github.vo.Resource
import javax.inject.Inject
import javax.inject.Singleton

/**
 *Time:2020/9/25
 *Author:HevenHolt
 *Description:
 */
@Singleton
class RepoRepository @Inject constructor(
    private val githubService: GithubService,
    private val db: GithubDb,
    private val repoDao: RepoDao
) {

    suspend fun search(query:String): RepoSearchResponse {
        return githubService.searchRepos(query)
    }

//    fun search(query: String): LiveData<Resource<RepoSearchResponse>> =
//        object : NetworkResource<RepoSearchResponse>() {
//            override fun createCall(): LiveData<ApiResponse<RepoSearchResponse>> {
//                return githubService.searchRepos(query)
//            }
//        }.asLiveData()

//    fun search(query: String): LiveData<Resource<List<Repo>>> =
//        object : NetworkBoundResource<List<Repo>, RepoSearchResponse>() {
//            override fun createCall(): LiveData<ApiResponse<RepoSearchResponse>> =
//                githubService.searchRepos(query)
//
//            override suspend fun saveCallResult(item: RepoSearchResponse) {
//                val repoIds = item.items.map { it.id }
//                val repoSearchResult = RepoSearchResult(
//                    query = query,
//                    repoIds = repoIds,
//                    totalCount = item.total,
//                    next = item.nextPage
//                )
//                db.runInTransaction {
//                    repoDao.insertRepos(item.items)
//                    repoDao.insert(repoSearchResult)
//                }
//            }
//
//            override fun loadFromDb(): LiveData<List<Repo>> {
//                return repoDao.search(query).switchMap { searchData ->
//                    if (searchData == null) {
//                        AbsentLiveData.create()
//                    } else {
//                        repoDao.loadOrdered(searchData.repoIds)
//                    }
//                }
//            }
//
//            override fun shouldFetch(data: List<Repo>?): Boolean = data == null
//
//            override fun processResponse(response: ApiSuccessResponse<RepoSearchResponse>): RepoSearchResponse {
//                val body = response.body
//                body.nextPage = response.nextPage
//                return body
//            }
//
//        }.asLiveData()

}