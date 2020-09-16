package kr.co.example.codingtest_hand.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.co.example.codingtest_hand.database.entity.Ranking


@Dao
interface RankingDAO {

    //dummy
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRankingList(data: List<Ranking>)

    @Query("SELECT * FROM Ranking")
    fun dummySelect(): List<Ranking>
    //dummy

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRanking(data: Ranking)

    @Query("SELECT * FROM Ranking ORDER BY totalGameScore DESC")
    fun selectAllDESC() : LiveData<List<Ranking>>

    @Query("SELECT * FROM Ranking ORDER BY totalGameScore ASC")
    fun selectAllAES(): LiveData<List<Ranking>>

    @Query("SELECT * FROM Ranking WHERE team = :teamName")
    fun selectRanking(teamName: String): LiveData<List<Ranking>>

    @Query("DELETE FROM Ranking WHERE team = :teamName")
    fun deleteRanking(teamName:String)
}