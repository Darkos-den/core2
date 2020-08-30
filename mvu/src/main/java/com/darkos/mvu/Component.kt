package com.darkos.mvu

import com.darkos.mvu.models.MVUState

interface Component<T : MVUState> {
    fun render(state: T)
}

