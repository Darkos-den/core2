package com.darkos.mvu

import com.darkos.mvu.models.Effect
import com.darkos.mvu.models.Message

typealias effectHandler = suspend (effect: Effect) -> Message

interface EffectHandler {
    suspend fun call(effect: Effect): Message
}

fun effectHandler(block: suspend (effect: Effect) -> Message) = object : EffectHandler {
    override suspend fun call(effect: Effect): Message {
        return block(effect)
    }
}