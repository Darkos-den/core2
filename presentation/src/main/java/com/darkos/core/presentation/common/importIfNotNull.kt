package com.darkos.core.presentation.common

import org.kodein.di.Kodein

fun Kodein.MainBuilder.importIfNotNull(module: Kodein.Module?) {
    module?.let {
        import(it, true)
    }
}