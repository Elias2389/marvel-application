package com.ae.marvelapplication.ui.characterlist.adapter

import android.view.View
import coil.load
import com.ae.marvelapplication.R
import com.ae.marvelapplication.common.listener.SelectItemListener
import com.ae.marvelapplication.databinding.CharacterAppItemListBinding
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.xwray.groupie.viewbinding.BindableItem

class CharacterItem(
    private val character: ResultsItem,
    private val listener: SelectItemListener
) : BindableItem<CharacterAppItemListBinding>() {

    override fun initializeViewBinding(view: View): CharacterAppItemListBinding {
        return CharacterAppItemListBinding.bind(view)
    }

    override fun bind(viewBinding: CharacterAppItemListBinding, position: Int) {
        viewBinding.title.text = character.name
        viewBinding.itemViewCharacter.setOnClickListener { listener.goToDetail(character) }
        setImage(viewBinding)
    }

    private fun setImage(binding: CharacterAppItemListBinding) {
        binding.image.load(character.thumbnail.path + IMAGE_VARIANT)
    }

    override fun getLayout(): Int = R.layout.character_app_item_list

    private companion object {
        const val IMAGE_VARIANT: String = "/portrait_small.jpg"
    }
}