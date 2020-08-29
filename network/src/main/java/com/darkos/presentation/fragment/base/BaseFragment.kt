package com.darkos.presentation.fragment.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.darkos.presentation.common.ActivityResultProcessor
import com.darkos.presentation.viewModel.BaseViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

abstract class BaseFragment : KodeinFragment(), KodeinAware {

    private val resultProcessor: ActivityResultProcessor by instance<ActivityResultProcessor>()
    abstract val viewModel: BaseViewModel

    //region lifecycle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(viewModel)
        viewCreated(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultProcessor.onActivityResult(requestCode, resultCode, data)
    }

    //endregion

    abstract fun viewCreated(savedInstanceState: Bundle?)
}
