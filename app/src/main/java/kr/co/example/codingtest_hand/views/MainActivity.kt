package kr.co.example.codingtest_hand.views

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import kr.co.example.codingtest_hand.R
import kr.co.example.codingtest_hand.base.BaseActivity
import kr.co.example.codingtest_hand.base.BaseViewModelFactory
import kr.co.example.codingtest_hand.database.AppDatabase
import kr.co.example.codingtest_hand.database.entity.Ranking
import kr.co.example.codingtest_hand.databinding.ActivityMainBinding
import kr.co.example.codingtest_hand.utils.PLog
import kr.co.example.codingtest_hand.utils.Utils
import kr.co.example.codingtest_hand.utils.ioThread
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var factory: BaseViewModelFactory


    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = binding.vm as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val v = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

        getViewModel().hideKeyBoardFlag.observe(this, Observer {
            if (it) Utils.hideKeyboard(this, currentFocus)
        })

        getViewModel().systemMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })




    }




}