package kr.co.example.codingtest_hand.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection


abstract class BaseActivity<T: ViewDataBinding> : FragmentActivity() {

    protected lateinit var binding: T

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        super.setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null))
        binding = DataBindingUtil.setContentView(this, getLayoutId())
//        binding.lifecycleOwner = this
//        binding.executePendingBindings()
    }



}