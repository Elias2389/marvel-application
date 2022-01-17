package com.ae.marvelapplication.ui.characterdetail.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ae.marvelapplication.databinding.CharacterAppFragmentCharacterDetailBinding
import com.ae.marvelapplication.ui.characterdetail.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val binding: CharacterAppFragmentCharacterDetailBinding by lazy {
        CharacterAppFragmentCharacterDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

}