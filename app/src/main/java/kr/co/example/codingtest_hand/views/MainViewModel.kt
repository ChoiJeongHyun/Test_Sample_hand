package kr.co.example.codingtest_hand.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kr.co.example.codingtest_hand.base.BaseViewModel
import kr.co.example.codingtest_hand.database.entity.Ranking
import kr.co.example.codingtest_hand.repository.AppRepository
import kr.co.example.codingtest_hand.utils.PLog
import kr.co.example.codingtest_hand.utils.ioThread
import javax.inject.Inject

class MainViewModel @Inject constructor(private val appRepository: AppRepository) :
    BaseViewModel() {

    //true -> 내림차순 , false -> 오름차순
    private val _sortType: MutableLiveData<Boolean> = MutableLiveData(true)
    val sortType
    get() = _sortType
    val rankingItems: LiveData<List<Ranking>> = Transformations.switchMap(_sortType) {
        if (it) appRepository.getRankingItemsDESC() else appRepository.getRankingItemsAES()
    }

    private val _teamName: MutableLiveData<String> = MutableLiveData("")
    val teamName
    get() = _teamName

    private val _winCount: MutableLiveData<String> = MutableLiveData("")
    val winCount
    get() = _winCount

    private val _tieCount: MutableLiveData<String> = MutableLiveData("")
    val tieCount
    get() = _tieCount

    private val _loseCount: MutableLiveData<String> = MutableLiveData("")
    val loseCount
    get() = _loseCount

    private val _hideKeyBoardFlag: MutableLiveData<Boolean> = MutableLiveData(false)
    val hideKeyBoardFlag
        get() = _hideKeyBoardFlag

    private val _systemMsg: MutableLiveData<String> = MutableLiveData()
    val systemMsg
    get() = _systemMsg


    fun sortRefresh() {

        _sortType.value = !_sortType.value!!

    }

    fun rankingAdd() {
        _hideKeyBoardFlag.value = true
        if (teamName.value!! == "" ) {
            _systemMsg.value = "팀명을 입력해주세요."
            return
        }

        val ranking = Ranking().apply {
            team = teamName.value!!.toUpperCase()
            totalGameCount = if (winCount.value!!.toIntOrNull() == null ) 0 else winCount.value!!.toInt() +
                             if (tieCount.value!!.toIntOrNull() == null ) 0 else tieCount.value!!.toInt() +
                             if (loseCount.value!!.toIntOrNull() == null ) 0 else loseCount.value!!.toInt()

            gameWinCount = if (winCount.value!!.toIntOrNull() == null ) 0 else winCount.value!!.toInt()
            gameTieCount = if (tieCount.value!!.toIntOrNull() == null ) 0 else tieCount.value!!.toInt()
            gameLoseCount = if (loseCount.value!!.toIntOrNull() == null ) 0 else loseCount.value!!.toInt()
            totalGameScore = (gameWinCount * 3) + gameTieCount

        }

        _teamName.value = ""
        winCount.value = ""
        tieCount.value = ""
        loseCount.value = ""

        ioThread {
            appRepository.insertRanking(ranking)
        }

    }

    fun rankingRemove(teamName : String): Boolean {
        ioThread {
            appRepository.deleteRanking(teamName)
        }
        _systemMsg.value = "삭제하였습니다."
        return false
    }


}