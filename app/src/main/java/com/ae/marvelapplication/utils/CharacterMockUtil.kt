package com.ae.marvelapplication.utils

import com.ae.marvelapplication.dto.dto.Comics
import com.ae.marvelapplication.dto.dto.Events
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.dto.dto.Series
import com.ae.marvelapplication.dto.dto.Stories
import com.ae.marvelapplication.dto.dto.Thumbnail

val characterMock: ResultsItem = ResultsItem(
    id = 12334,
    thumbnail = Thumbnail(),
    comics = Comics(),
    series = Series(),
    events = Events(),
    stories = Stories(),
    urls = emptyList()
)