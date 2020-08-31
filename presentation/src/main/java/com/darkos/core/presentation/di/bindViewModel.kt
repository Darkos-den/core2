package com.darkos.core.presentation.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darkos.core.presentation.activity.KodeinActivity
import com.darkos.core.presentation.fragment.argumented.ArgumentedViewModel
import com.darkos.core.presentation.fragment.base.KodeinFragment
import com.darkos.core.presentation.viewModel.BaseViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}

inline fun <reified VM, T> T.viewModel(): Lazy<VM>
        where T : KodeinAware,
              T : Fragment,
              VM : ViewModel,
              VM : BaseViewModel {
    return lazy {
        ViewModelProvider(context as AppCompatActivity, direct.instance(tag = KodeinFragment.DI_TAG))
            .get(VM::class.java).let {
                return@let it
            }
    }
}

inline fun <reified VM, T> T.viewModel(): Lazy<VM>
        where T : KodeinAware,
              T : AppCompatActivity,
              VM : ViewModel,
              VM : BaseViewModel {
    return lazy {
        ViewModelProvider(this as AppCompatActivity, direct.instance(tag = KodeinActivity.DI_TAG))
            .get(VM::class.java).let {
                return@let it
            }
    }
}

inline fun <reified VM, T> T.argumentedViewModel(): Lazy<VM>
        where T : KodeinAware,
              T : Fragment,
              VM : ViewModel,
              VM : ArgumentedViewModel<*> {
    return lazy {
        ViewModelProvider(context as AppCompatActivity, direct.instance(tag = KodeinFragment.DI_TAG))
            .get(VM::class.java).let {
                return@let it
            }
    }
}