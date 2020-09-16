package kr.co.example.codingtest_hand.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kr.co.example.codingtest_hand.views.MainActivity
import kr.co.example.codingtest_hand.di.ActivityScope

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}