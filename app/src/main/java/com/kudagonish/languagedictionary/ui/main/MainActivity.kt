package com.kudagonish.languagedictionary.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudagonish.models.AppState
import com.kudagonish.models.View
import com.kudagonish.languagedictionary.ui.base.BaseActivity
import com.kudagonish.languagedictionary.ui.desctiption.DescriptionActivity
import com.kudagonish.history.HistoryActivity
import com.kudagonish.models.R
import com.kudagonish.models.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<AppState>(), View {

    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    override val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

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
        adapter = MainAdapter {
            startActivity(
                DescriptionActivity.getIntent(
                    this,
                    word = it.text.orEmpty(),
                    description = it.meaning?.joinToString {it.translation?.translation.orEmpty()}.toString(),
                    imageUrl = it.meaning?.firstOrNull()?.imageUrl
                )
            )
        }
        binding.mainActivityRecyclerview.adapter = adapter


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.history_item ->{
                startActivity(com.kudagonish.history.HistoryActivity.createIntent(this))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
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
                if (appState.process != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.process!!
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.t.message)
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