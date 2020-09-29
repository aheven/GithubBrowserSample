package heven.holt.jetpack.github.vo

import androidx.room.Entity
import androidx.room.TypeConverters
import heven.holt.jetpack.github.db.GithubTypeConverters

/**
 *Time:2020/9/28
 *Author:HevenHolt
 *Description:
 */
@Entity(primaryKeys = ["query"])
@TypeConverters(GithubTypeConverters::class)
data class RepoSearchResult(
    val query: String,
    val repoIds: List<Int>,
    val totalCount: Int,
    val next: Int?
)