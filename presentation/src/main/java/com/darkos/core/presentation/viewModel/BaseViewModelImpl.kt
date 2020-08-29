package com.darkos.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {

    private val ioJob = SupervisorJob()
    private val mainJob = SupervisorJob()

    private val defaultScope = CoroutineScope(Dispatchers.Main)
    private val viewModelMainScope = CoroutineScope(Dispatchers.Main + mainJob)
    private val viewModelIoScope = CoroutineScope(Dispatchers.IO + ioJob)

    protected val ioScope = viewModelIoScope
    protected val mainScope = viewModelMainScope

    override fun onCleared() {
        ioJob.cancelChildren()
        ioJob.cancel()
        mainJob.cancelChildren()
        mainJob.cancel()
        super.onCleared()
    }
}