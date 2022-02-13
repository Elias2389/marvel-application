package com.ae.marvelapplication.presentation.view

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ae.domain.model.Resource
import com.ae.marvelapplication.R
import com.ae.marvelapplication.common.base.BaseActivity
import com.ae.marvelapplication.common.listener.SelectItemListener
import com.ae.marvelapplication.databinding.CharacterAppActivityCharactersListBinding
import com.ae.domain.model.ResultsItem
import com.ae.marvelapplication.mapper.toParcelable
import com.ae.marvelapplication.presentation.adapter.CharacterItem
import com.ae.marvelapplication.presentation.viewmodel.CharacterListViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharactersListActivity : BaseActivity(), SelectItemListener {

    private lateinit var binding: CharacterAppActivityCharactersListBinding

    private val viewModel: CharacterListViewModel by viewModels()
    private val scrollingSection: Section = Section()
    private lateinit var groupAdapter: GroupieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CharacterAppActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupViewModel()
        setupViews()
        setupAdapter()
    }

    private fun setupViews() {
        configureEmptyState(
            binding.rvListItems,
            binding.emptyStateCharacterListView,
            R.drawable.general_error_image
        )
    }

    private fun setupAdapter() {
        groupAdapter = GroupieAdapter()
        groupAdapter.add(scrollingSection)

        binding.rvListItems.apply {
            adapter = groupAdapter
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@CharactersListActivity, SPAN_COUNT)
            addOnScrollListener(onScrollListener)
        }
    }

    private fun setupActionBar() {
        supportActionBar?.run {
            title = resources.getString(R.string.character_app_fragment_list_title)
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.character_app_blue_primary)))
        }
    }

    private fun setupViewModel() {
        viewModel.getItems().observe(this, Observer(this::handlerResponse))
    }

    fun handlerResponse(result: Resource<List<ResultsItem>>) {
        when (result) {
            is Resource.Success -> {
                viewModel.isLoading = false
                setListAdapter(result.data)
            }
            is Resource.Error -> showEmptyState(getString(R.string.character_app_general_error))
            else -> Timber.e(getString(R.string.character_app_general_error))
        }
    }

    private val onScrollListener: RecyclerView.OnScrollListener by lazy {
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

                viewModel.onLoadMoreItems(
                    visibleItemCount,
                    firstVisibleItemPosition,
                    totalItemCount
                )
            }
        }
    }

    private fun setListAdapter(response: List<ResultsItem>) {
        showList()
        val list: List<CharacterItem> = response.map { CharacterItem(it, this) }
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

    override fun goToDetail(character: ResultsItem) {
        val bundle = bundleOf(CHARACTER_SELECTED to character.toParcelable())
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private companion object {
        const val CHARACTER_SELECTED: String = "characterSelected"
        const val SPAN_COUNT: Int = 3
    }
}