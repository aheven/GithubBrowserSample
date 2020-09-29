//package heven.holt.jetpack.github.api
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import heven.holt.jetpack.github.util.LiveDataCallAdapterFactory
//import heven.holt.jetpack.github.util.getOrAwaitValue
//import okhttp3.mockwebserver.MockResponse
//import okhttp3.mockwebserver.MockWebServer
//import okio.Okio
//import org.hamcrest.CoreMatchers
//import org.hamcrest.CoreMatchers.notNullValue
//import org.hamcrest.MatcherAssert.assertThat
//import org.hamcrest.core.Is.`is`
//import org.hamcrest.core.IsNull
//import org.junit.*
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
///**
// *Time:2020/9/27
// *Author:HevenHolt
// *Description:
// */
//@RunWith(JUnit4::class)
//class GithubServiceTest {
//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var service: GithubService
//
//    private lateinit var mockWebServer: MockWebServer
//
//    @Before
//    fun createService() {
//        mockWebServer = MockWebServer()
//        service = Retrofit.Builder()
//            .baseUrl(mockWebServer.url("/"))
//            .addConverterFactory(GsonConverterFactory.create())
////            .addCallAdapterFactory(LiveDataCallAdapterFactory())
//            .build()
//            .create(GithubService::class.java)
//    }
//
//    @After
//    fun stopService() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun getUser() {
//        enqueueResponse("user-yigit.json")
//        val yigit = (service.getUser("yigit").getOrAwaitValue() as ApiSuccessResponse).body
//
//        val request = mockWebServer.takeRequest()
//        assertThat(request.path, `is`("/users/yigit"))
//
//        assertThat(yigit, notNullValue())
//        assertThat(yigit.avatarUrl, `is`("https://avatars3.githubusercontent.com/u/89202?v=3"))
//        assertThat(yigit.company, `is`("Google"))
//        assertThat(yigit.blog, `is`("birbit.com"))
//    }
//
//    @Test
//    fun getRepos() {
//        enqueueResponse("repos-yigit.json")
//        val repos = (service.getRepos("yigit").getOrAwaitValue() as ApiSuccessResponse).body
//
//        val request = mockWebServer.takeRequest()
//        Assert.assertThat(request.path, CoreMatchers.`is`("/users/yigit/repos"))
//
//        Assert.assertThat(repos.size, CoreMatchers.`is`(2))
//
//        val repo = repos[0]
//        Assert.assertThat(repo.fullName, CoreMatchers.`is`("yigit/AckMate"))
//
//        val owner = repo.owner
//        Assert.assertThat(owner, IsNull.notNullValue())
//        Assert.assertThat(owner.login, CoreMatchers.`is`("yigit"))
//        Assert.assertThat(owner.url, CoreMatchers.`is`("https://api.github.com/users/yigit"))
//
//        val repo2 = repos[1]
//        Assert.assertThat(repo2.fullName, CoreMatchers.`is`("yigit/android-architecture"))
//    }
//
//    @Test
//    fun getContributors() {
//        enqueueResponse("contributors.json")
//        val value = service.getContributors("foo", "bar").getOrAwaitValue()
//        val contributors = (value as ApiSuccessResponse).body
//        Assert.assertThat(contributors.size, CoreMatchers.`is`(3))
//        val yigit = contributors[0]
//        Assert.assertThat(yigit.login, CoreMatchers.`is`("yigit"))
//        Assert.assertThat(
//            yigit.avatarUrl,
//            CoreMatchers.`is`("https://avatars3.githubusercontent.com/u/89202?v=3")
//        )
//        Assert.assertThat(yigit.contributions, CoreMatchers.`is`(291))
//        Assert.assertThat(contributors[1].login, CoreMatchers.`is`("guavabot"))
//        Assert.assertThat(contributors[2].login, CoreMatchers.`is`("coltin"))
//    }
//
//    @Test
//    fun search() {
//        val next = """<https://api.github.com/search/repositories?q=foo&page=2>; rel="next""""
//        val last = """<https://api.github.com/search/repositories?q=foo&page=34>; rel="last""""
//        enqueueResponse(
//            "search.json", mapOf(
//                "link" to "$next,$last"
//            )
//        )
//        val response = service.searchRepos("foo").getOrAwaitValue() as ApiSuccessResponse
//
//        Assert.assertThat(response, IsNull.notNullValue())
//        Assert.assertThat(response.body.total, CoreMatchers.`is`(41))
//        Assert.assertThat(response.body.items.size, CoreMatchers.`is`(30))
//        Assert.assertThat<String>(
//            response.links["next"],
//            CoreMatchers.`is`("https://api.github.com/search/repositories?q=foo&page=2")
//        )
//        Assert.assertThat<Int>(response.nextPage, CoreMatchers.`is`(2))
//    }
//
//    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
//        val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
//        val source = Okio.buffer(Okio.source(inputStream))
//        val mockResponse = MockResponse()
//        for ((key, value) in headers) {
//            mockResponse.addHeader(key, value)
//        }
//        mockWebServer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
//    }
//}