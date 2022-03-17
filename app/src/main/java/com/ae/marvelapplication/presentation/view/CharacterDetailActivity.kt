package com.ae.marvelapplication.presentation.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.ae.domain.model.ItemsItem
import com.ae.domain.model.Resource
import com.ae.domain.model.ResultsItem
import com.ae.marvelapplication.R
import com.ae.marvelapplication.common.base.BaseActivity
import com.ae.marvelapplication.databinding.CharacterAppActivityCharacterDetailBinding
import com.ae.marvelapplication.parcelable.ResultsItemParcelable
import com.ae.marvelapplication.presentation.adapter.CharacterComicsDetail
import com.ae.marvelapplication.presentation.viewmodel.CharacterDetailViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailActivity : BaseActivity() {

    private lateinit var binding: CharacterAppActivityCharacterDetailBinding

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val scrollingSection: Section = Section()
    private lateinit var groupAdapter: GroupieAdapter
    private var characterSelected: ResultsItemParcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterAppActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupAdapter()
        getCharacter()
        getDetail()
        setupActionBar()
    }

    private fun setupActionBar() {
        supportActionBar?.run {
            title = resources.getString(R.string.character_app_fragment_detail_title)
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.character_app_blue_primary)))
        }
    }

    private fun setupAdapter() {
        groupAdapter = GroupieAdapter()
        groupAdapter.add(scrollingSection)

        binding.rvCharacterDetailItems.apply {
            adapter = groupAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CharacterDetailActivity)
        }
    }

    private fun getCharacter() {
        intent.extras?.let {
            characterSelected = it.getParcelable(CHARACTER_SELECTED)
        }
    }

    private fun getDetail() {
        characterSelected?.let {
            binding.textViewTitle.text = it.name
            viewModel.getCharacterById(it.id).observe(this, Observer(::handlerResponse))
        } ?: kotlin.run {
            showEmptyState(getString(R.string.character_app_general_error))
        }
    }

    fun handlerResponse(result: Resource<List<ResultsItem>>) {
        when (result) {
            is Resource.Success -> result.data.let { response ->
                val data = response[0]
                setPrincipalImage(data.thumbnail.path)
                setListAdapter(data.comics.items)
            }
            is Resource.Error -> showEmptyState(getString(R.string.character_app_general_error))
            else -> Timber.e(getString(R.string.character_app_general_error))
        }
    }

    private fun setListAdapter(response: List<ItemsItem>) {
        showList()
        val list: List<CharacterComicsDetail> = response.map { CharacterComicsDetail(it) }
        scrollingSection.apply { addAll(list) }
    }

    private fun showList() {
        setImageEmptyState(R.drawable.not_found_image)
        hideEmptyState()
    }

    private fun showEmptyState(message: String) {
        setMessageEmptyState(message)
        showEmptyState()
    }

    private fun setupViews() {
        configureEmptyState(
            binding.constraintLayoutCharacterDetailContainer,
            binding.emptyStateCharacterDetailView,
            R.drawable.not_found_image
        )
    }

    private fun setPrincipalImage(url: String) {
        binding.imageViewPrincipal.load(url + IMAGE_VARIANT)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private companion object {
        const val CHARACTER_SELECTED: String = "characterSelected"
        const val IMAGE_VARIANT: String = "/landscape_amazing.jpg"
    }
}