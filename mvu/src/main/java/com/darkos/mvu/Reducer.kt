package com.darkos.mvu

import com.darkos.mvu.models.MVUState
import com.darkos.mvu.models.Message
import com.darkos.mvu.models.StateCmdData

interface Reducer<T : MVUState> {
    fun update(state: T, message: Message): StateCmdData<T>
}

fun <T : MVUState> reducer(block: (state: T, message: Message) -> StateCmdData<T>) =
    object : Reducer<T> {
        override fun update(state: T, message: Message): StateCmdData<T> {
            return block(state, message)
        }
    }