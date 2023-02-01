package com.kudagonish.languagedictionary.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudagonish.languagedictionary.*
import com.kudagonish.languagedictionary.databinding.ActivityMainBinding
import com.kudagonish.languagedictionary.di.ViewModelFactory
import com.kudagonish.languagedictionary.ui.base.BaseActivity
import com.kudagonish.languagedictionary.ui.base.BaseViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : BaseActivity<AppState>(), View {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory




    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    override val model: MainViewModel by lazy {
        viewModelFactory.create(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    model.getWordsDescriptions(searchWord, true)
                }
            })
            searchDialogFragment.show(
                supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )

        }
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        adapter = MainAdapter { }
        binding.mainActivityRecyclerview.adapter = adapter


    }

    override fun renderData(appSate: AppState) {
        when (appSate) {
            is AppState.Success -> {
                val dataModel = appSate.data
                if (dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter { }
                    } else {
                        adapter!!.submitList(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appSate.process != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appSate.process
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appSate.t.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getWordsDescriptions("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}