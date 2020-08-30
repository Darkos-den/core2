package com.darkos.core.presentation.fragment.argumented

import android.os.Bundle
import android.view.View
import com.darkos.core.presentation.fragment.base.BaseFragment

abstract class ArgumentedFragment<A : BaseArguments> : BaseFragment(), Argumented<A> {

    abstract override val viewModel: ArgumentedViewModel<A>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments!!.getParcelable<A>(KEY_ARGS)
        if (args != null) {
            viewModel.applyArguments(args)
        }
    }

    companion object {
        const val KEY_ARGS = "key_args"
    }
}