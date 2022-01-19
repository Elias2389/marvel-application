package com.ae.marvelapplication.ui.characterdetail.adapter

import android.view.View
import com.ae.marvelapplication.R
import com.ae.marvelapplication.databinding.CharacterAppItemComicBinding
import com.ae.marvelapplication.dto.dto.ItemsItem
import com.xwray.groupie.viewbinding.BindableItem

class CharacterComicsDetail(
    private val item: ItemsItem
) : BindableItem<CharacterAppItemComicBinding>() {

    override fun initializeViewBinding(view: View): CharacterAppItemComicBinding {
        return CharacterAppItemComicBinding.bind(view)
    }

    override fun bind(viewBinding: CharacterAppItemComicBinding, position: Int) {
        viewBinding.title.text = item.name
    }

    override fun getLayout(): Int = R.layout.character_app_item_comic
}