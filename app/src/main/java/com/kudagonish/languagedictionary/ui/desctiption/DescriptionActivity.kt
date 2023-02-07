package com.kudagonish.languagedictionary.ui.desctiption

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.kudagonish.languagedictionary.R
import com.kudagonish.languagedictionary.databinding.AcDescriptionBinding
import com.kudagonish.languagedictionary.ui.base.isOnline
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.lang.Exception

class DescriptionActivity : AppCompatActivity() {
    private val binding by lazy { AcDescriptionBinding.inflate(layoutInflater) }

    private val word by lazy { intent.extras?.getString(KEY_WORD).orEmpty() }
    private val description by lazy { intent.extras?.getString(KEY_DESCRIPTION).orEmpty() }
    private val imageUrl by lazy { intent.extras?.getString(KEY_IMAGE_URL) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setActionBarHomeButton()
        binding.root.setOnClickListener {
            startLoadingShowError()
        }
        binding.root.isRefreshing = true
        setData()
    }

    private fun startLoadingShowError() {
        if (isOnline(this)) {
            setData()
        } else {
            //todo
            stopLoading()
        }
    }


    fun setData() {
        binding.descriptionHeader.text = word
        binding.descriptionText.text = description
        val imageUrl = imageUrl
        if (imageUrl == null) {
            stopLoading()
        } else {
            usePicassoLoading(imageUrl)
            //useGlideLoading(imageUrl)
        }
    }

    private fun stopLoading() {
        binding.root.isRefreshing = false
    }

    private fun useCoilLoading(imageUrl: String){
        val request = ImageRequest.Builder(this)
            .data("https://$imageUrl")
            .target(
                onStart = {},
                onSuccess = {
                        result ->
                    stopLoading()
                    binding.desctiptionImage.setImageDrawable(result)
                },
                onError = {
                    stopLoading()
                    binding.desctiptionImage.setImageResource(R.drawable.ic_launcher_foreground)
                }

            )
            .transformations(
                CircleCropTransformation()
            )
            .build()
        lifecycleScope.launch{
            ImageLoader(this@DescriptionActivity).execute(request)
        }

    }
    @SuppressLint("CheckResult")
    private fun useGlideLoading(imageUrl: String) {
        Glide.with(binding.desctiptionImage)
            .load("https://$imageUrl")
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoading()
                    binding.desctiptionImage.setImageResource(R.drawable.ic_launcher_foreground)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    stopLoading()
                    return false
                }

            })
            .apply{
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .centerCrop()
            }
            .into(binding.desctiptionImage)
    }

    private fun usePicassoLoading(imageUrl: String) {
        Picasso.get().load("https://$imageUrl")
            .placeholder(R.drawable.ic_launcher_foreground).fit().centerCrop()
            .into(binding.desctiptionImage, object : Callback {
                override fun onSuccess() {
                    stopLoading()
                }

                override fun onError(e: Exception?) {
                    stopLoading()
                    binding.desctiptionImage.setImageResource(R.drawable.ic_launcher_foreground)
                }

            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionBarHomeButton() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {

        private val KEY_WORD = "KEY_WORD"
        private val KEY_DESCRIPTION = "KEY_DESCRIPTION"
        private val KEY_IMAGE_URL = "KEY_IMAGE_URL"

        fun getIntent(context: Context, word: String, description: String, imageUrl: String?) =
            Intent(context, DescriptionActivity::class.java).apply {
                putExtra(KEY_WORD, word)
                putExtra(KEY_DESCRIPTION, description)
                putExtra(KEY_IMAGE_URL, imageUrl)
            }
    }

}