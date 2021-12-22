package com.akribase.contextualcards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.akribase.contextualcards.R
import com.akribase.contextualcards.databinding.ActivityMainBinding
import com.akribase.contextualcards.ui.adapters.contextualview.ContextualViewAdapter

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContextualViewAdapter { viewModel.remove(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRv()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        binding.refresh.setOnRefreshListener { viewModel.fetchUISpec() }
        viewModel.isFetching.observe(this) { binding.refresh.isRefreshing = it }
    }

    private fun initRv() {
        binding.rv.adapter = adapter
        viewModel.uiSpec.observe(this) { adapter.submitList(it) }
    }
}