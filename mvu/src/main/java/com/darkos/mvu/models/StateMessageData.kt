package com.darkos.mvu.models

data class StateMessageData<T : MVUState>(
    val state: T,
    val message: Message
)