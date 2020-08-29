package com.darkos.presentation.fragment.argumented

import com.darkos.presentation.viewModel.BaseViewModel

interface ArgumentedViewModel<A : BaseArguments> : BaseViewModel {
    fun applyArguments(args: A)
}