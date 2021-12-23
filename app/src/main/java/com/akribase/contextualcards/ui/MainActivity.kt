package com.akribase.contextualcards.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.akribase.cardcomponent.models.data.CardGroup
import com.akribase.cardcomponent.ui.CardComponent
import com.akribase.contextualcards.R
import com.akribase.contextualcards.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initComponent(binding.component)
        initErrorDisplay()
    }

    private fun initErrorDisplay() {
        viewModel.isConnectionError.observe(this) {
            binding.errorGroup.visibility = if (it) View.VISIBLE else View.GONE
        }

        binding.retryBtn.setOnClickListener { viewModel.fetchUISpec() }
    }

    private fun initComponent(component: CardComponent) {
        component.onFetch = { viewModel.fetchUISpec() }
        component.onHC3Remove = { viewModel.remove(it) }
        viewModel.isFetching.observe(this) { component.isLoading = it }
        viewModel.uiSpec.observe(this) { component.render(it) }
    }

}