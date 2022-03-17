package com.ae.marvelapplication.common.listener

import com.ae.domain.model.ResultsItem

interface SelectItemListener {
    fun goToDetail(character: ResultsItem)
}