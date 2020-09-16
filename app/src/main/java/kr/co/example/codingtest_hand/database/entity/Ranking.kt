package kr.co.example.codingtest_hand.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Ranking")
data class Ranking (
    @PrimaryKey
    var team: String = "",
    var totalGameCount: Int = 0,
    var gameWinCount: Int = 0,
    var gameTieCount: Int = 0,
    var gameLoseCount: Int = 0,
    var totalGameScore: Int = 0
)
