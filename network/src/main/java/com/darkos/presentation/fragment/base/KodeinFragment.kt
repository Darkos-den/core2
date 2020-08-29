package com.darkos.presentation.fragment.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.darkos.presentation.common.importIfNotNull
import com.darkos.presentation.viewModel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.x.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

abstract class KodeinFragment : Fragment(), KodeinAware {

    private val _parentKodein: Kodein by this.closestKodein()
    override val kodein: Kodein = Kodein.lazy {
        extend(_parentKodein, true)
        with(parentFragment) {
            if (this is BaseFragment) {
                extend(kodein, true)
            }
        }

        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(direct) }

        importIfNotNull(viewModelModule)
        importIfNotNull(kodeinModule)
    }

    open val kodeinModule: Kodein.Module? = null
    open val viewModelModule: Kodein.Module? = null

    override val kodeinTrigger = KodeinTrigger()

    override fun onAttach(context: Context) {
        kodeinTrigger.trigger()
        super.onAttach(context)
    }
}