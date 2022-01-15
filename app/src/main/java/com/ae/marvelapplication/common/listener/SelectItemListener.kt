package com.ae.marvelapplication.common.listener

import com.ae.marvelapplication.dto.dto.ResultsItem

interface SelectItemListener {
    fun goToDetail(character: ResultsItem)
}