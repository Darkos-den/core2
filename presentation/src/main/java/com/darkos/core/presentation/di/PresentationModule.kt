package com.darkos.core.presentation.di

import com.darkos.core.presentation.common.ActivityResultProcessor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

object PresentationModule {

    fun get() = Kodein.Module("Core.Presentation") {
        bind() from singleton {
            ActivityResultProcessor()
        }
    }
}