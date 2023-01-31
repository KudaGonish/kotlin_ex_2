package com.kudagonish.languagedictionary.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudagonish.languagedictionary.*
import com.kudagonish.languagedictionary.databinding.ActivityMainBinding
import com.kudagonish.languagedictionary.ui.base.BaseActivity


class MainActivity : BaseActivity<AppSate>() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null


    override fun createPresenter(): Presenter<AppSate, View> {
        return MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(
                supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )

        }
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        adapter = MainAdapter {  }
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appSate: AppSate) {
        when (appSate) {
            is AppSate.Success -> {
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
            is AppSate.Loading -> {
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
            is AppSate.Error -> {
                showErrorScreen(appSate.t.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            presenter.getData("hi", true)
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