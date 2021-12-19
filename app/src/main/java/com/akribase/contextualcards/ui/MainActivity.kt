package com.akribase.contextualcards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.akribase.contextualcards.R
import com.akribase.contextualcards.databinding.ActivityMainBinding
import com.akribase.contextualcards.ui.adapters.ContextualViewAdapter

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRv()
    }

    private fun initRv() {
        viewModel.uiSpec.observe(this) {
            binding.rv.adapter = ContextualViewAdapter(it)
        }
    }
}