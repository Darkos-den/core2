package com.darkos.mvu.models

data class StateCmdData<T: MVUState>(
    val state: T,
    val effect: Effect
)