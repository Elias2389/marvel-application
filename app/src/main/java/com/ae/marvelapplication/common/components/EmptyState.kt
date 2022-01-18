package com.ae.marvelapplication.common.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.ae.marvelapplication.R
import com.ae.marvelapplication.databinding.CharacterAppEmptyStateViewBinding
import com.ae.marvelapplication.utils.hide
import com.ae.marvelapplication.utils.show

/**
 * Component to show empty state
 */
class EmptyState : ConstraintLayout {

    private var binding: CharacterAppEmptyStateViewBinding = CharacterAppEmptyStateViewBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * Set message of empty state
     * @param message about empty state
     */
    fun setMessage(message: String) {
        binding.emptyStateTaskText.text = message
    }

    /**
     * Set image of empty state
     * @param idImage id of image of empty state
     */
    @SuppressLint("ResourceType", "UseCompatLoadingForDrawables")
    fun setImage(@IdRes idImage: Int) {
        val drawable = rootView.resources.getDrawable(idImage)
        drawable.let {
            binding.emptyStateTaskImage.setImageDrawable(it)
        }
    }

    /**
     * Method to show empty state
     */
    fun showEmptyState() {
        binding.emptyStateLoading.hide()
        binding.emptyStateTaskText.show()
        binding.emptyStateTaskImage.show()
    }

    /**
     * Method to show empty state when is http error
     */
    fun showEmptyStateErrorConnection() {
        setMessage(resources.getString(R.string.character_app_general_error))
//        setImage(R.drawable.error_connection_image)
        binding.emptyStateLoading.hide()
        binding.emptyStateTaskText.show()
        binding.emptyStateTaskImage.show()
    }

    /**
     * Method to hide empty state
     */
    fun hideEmptyState() {
        binding.emptyStateLoading.hide()
        binding.emptyStateTaskText.hide()
        binding.emptyStateTaskImage.hide()
    }
}
