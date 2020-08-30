package com.darkos.core.presentation.activity

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.darkos.core.presentation.common.importIfNotNull
import com.darkos.core.presentation.viewModel.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinTrigger
import org.kodein.di.android.closestKodein
import org.kodein.di.android.retainedKodein
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

abstract class KodeinActivity : AppCompatActivity(), KodeinAware {

    open val kodeinModule: Kodein.Module? = null
    open val viewModelModule: Kodein.Module? = null
    open val routerModule: Kodein.Module? = null

    private val _parentKodein by closestKodein()
    override val kodein: Kodein by retainedKodein {
        extend(_parentKodein)

        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(direct) }

        importIfNotNull(kodeinModule)
        importIfNotNull(viewModelModule)
        importIfNotNull(routerModule)
    }

    override val kodeinTrigger = KodeinTrigger()

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        kodeinTrigger.trigger()
        super.onCreate(savedInstanceState)
    }
}