package kr.co.example.codingtest_hand.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel: ViewModel() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}