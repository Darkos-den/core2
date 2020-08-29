package com.darkos.core.presentation.fragment.argumented

import com.darkos.core.presentation.viewModel.BaseViewModel

interface ArgumentedViewModel<A : BaseArguments> : BaseViewModel {
    fun applyArguments(args: A)
}