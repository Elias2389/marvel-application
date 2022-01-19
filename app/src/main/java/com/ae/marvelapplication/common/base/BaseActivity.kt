package com.ae.marvelapplication.common.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ae.marvelapplication.common.components.EmptyState
import com.ae.marvelapplication.utils.hide
import com.ae.marvelapplication.utils.show

/**
 * Base Fragment class.
 * Here you will find common initializations.
 */
abstract class BaseActivity : AppCompatActivity() {

    private lateinit var emptyStateView: EmptyState
    private lateinit var regularContainer: View

    protected fun setEmptyStateView(emptyStateView: EmptyState) {
        this.emptyStateView = emptyStateView
    }

    private fun getEmptyState(): EmptyState = emptyStateView

    protected fun showEmptyState() {
        getEmptyState().showEmptyState()
        getRegularContainer().hide()
    }

    protected fun hideEmptyState() {
        getEmptyState().hideEmptyState()
        getRegularContainer().show()
    }

    protected fun setMessageEmptyState(message: String) {
        getEmptyState().setMessage(message)
    }

    protected fun setImageEmptyState(idImage: Int) {
        getEmptyState().setImage(idImage)
    }

    protected fun setRegularContainer(container: View) {
        regularContainer = container
    }

    private fun getRegularContainer(): View = regularContainer

    protected fun configureEmptyState(container: View, emptyStateView: EmptyState, idImage: Int) {
        setRegularContainer(container)
        setEmptyStateView(emptyStateView)
        setImageEmptyState(idImage)
    }
}