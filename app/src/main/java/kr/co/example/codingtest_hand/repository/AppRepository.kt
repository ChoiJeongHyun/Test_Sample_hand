package kr.co.example.codingtest_hand.repository


import kr.co.example.codingtest_hand.api.ApiManager
import kr.co.example.codingtest_hand.api.ImageAPI
import kr.co.example.codingtest_hand.database.AppDatabase
import kr.co.example.codingtest_hand.database.entity.Ranking
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppRepository @Inject constructor(private val apiManager: ApiManager, private val appDatabase: AppDatabase ) {

    fun apiGetImg(s: SearchCondition) = apiManager.getRetrofitService(ImageAPI::class.java).getImg(query = s.query, sort = s.sort , page = s.page , size = s.size)

    fun getRankingItemsDESC() = appDatabase.rankingDAO().selectAllDESC()

    fun getRankingItemsAES() = appDatabase.rankingDAO().selectAllAES()

    fun getRankingItem(teamName: String) = appDatabase.rankingDAO().selectRanking(teamName)

    fun insertRanking(ranking: Ranking) = appDatabase.rankingDAO().insertRanking(ranking)

    fun deleteRanking(teamName: String) = appDatabase.rankingDAO().deleteRanking(teamName)
}