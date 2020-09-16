package kr.co.example.codingtest_hand.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kr.co.example.codingtest_hand.di.ViewModelKey
import kr.co.example.codingtest_hand.views.MainViewModel


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel) : ViewModel

}