package heven.holt.jetpack.github.db

import androidx.room.Database
import androidx.room.RoomDatabase
import heven.holt.jetpack.github.vo.Repo
import heven.holt.jetpack.github.vo.RepoSearchResult

/**
 *Time:2020/9/28
 *Author:HevenHolt
 *Description:
 */
@Database(
    entities = [
        Repo::class,
        RepoSearchResult::class],
    version = 3,
    exportSchema = false
)
abstract class GithubDb : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}